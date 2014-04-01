import scala.sys.process._
import scala.io._
import scala.util.parsing.json._
import scala.collection.mutable.{Map => MMap}

object VMStatMetrics {
	def main(args: Array[String]) {
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
				val url = "http://localhost:8080/db/usagestats/vm_stats"
				var json = JSONObject(Map("timestamp" -> System.currentTimeMillis, "data" -> JSONObject(event.toMap))).toString()
				val curlCmd = Seq("curl", "-s", "-H", "'Content-Type: application/json'", "-X", "POST", "-d", json, url)
				Process(curlCmd).run
			},
			_ => ())
		val proc = Process(vmstatCmd).run(io)
  }
}