import scala.sys.process._
import scala.io._
import scala.util.parsing.json._
import scala.collection.mutable.{Map => MMap}
import java.lang.System

object VMStatMetrics {

	val defaultConfig = Map("server" -> "http://localhost:8080", 
													"database" -> "usagestats", 
													"collection" -> "vm_stats")

	def loadConfig(args: Array[String]) : Map[String, String] = {
		if(args.length > 0) {
			val source = Source.fromFile(args(0))
			val jsonStr = source.mkString
			source.close()
			val result = JSON.parseFull(jsonStr)
			result match {
			  case Some(map: Map[String, String]) => map
			  case None => defaultConfig
			  case other => defaultConfig
			}
		} else {
			defaultConfig
		}
	}

	def main(args: Array[String]) {
		val config = loadConfig(args)
		val vmstatCmd = s"vm_stat"
		val io = new ProcessIO(
			_ => (),
			stdout => {
				val res = Source.fromInputStream(stdout).getLines.drop(1)
				val event = MMap[String, Long]()
				res.foreach(
					x => {
						val line = x.split(":( )+")
						val key = line(0)
						val value = line(1).stripSuffix(".").toLong
						event += (key -> value)
					})
				val url = config("server") + "/db/" + config("database") + "/" + config("collection")
				var json = JSONObject(Map("timestamp" -> System.currentTimeMillis, "data" -> JSONObject(event.toMap))).toString()
				val curlCmd = Seq("curl", "-s", "-H", "'Content-Type: application/json'", "-X", "POST", "-d", json, url)
				println(curlCmd)
				Process(curlCmd).run
			},
			_ => ())
		val proc = Process(vmstatCmd).run(io)
	}

}