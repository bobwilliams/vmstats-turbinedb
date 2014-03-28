import scala.sys.process._
import scala.io._
import scala.util.parsing.json._

val vmstatCmd = s"vm_stat"
val io = new ProcessIO(
	_ => (),
	stdout => {
		val res = Source.fromInputStream(stdout).getLines.drop(1)
		res.foreach(
			x => {
				val line = x.split(":( )+")
				val key = line(0)
				val value = line(1).stripSuffix(".").toInt
				var json = JSONObject(Map("timestamp" -> System.currentTimeMillis, "data" -> JSONObject(Map(key -> value)))).toString()
				val url = "http://localhost:8080/db/usagestats/vm_stats"
				val curlCmd = Seq("curl", "-s", "-H", "'Content-Type: application/json'", "-X", "POST", "-d", s"${json}", url)
				Process(curlCmd).run})},
	_ => ())
val proc = Process(vmstatCmd).run(io)