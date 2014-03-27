import scala.sys.process._
import scala.io._

val vmstatCmd = s"vm_stat"
val io = new ProcessIO(
	_ => (),
	stdout => {
		val res = Source.fromInputStream(stdout).getLines.drop(1)
		res.foreach(
			x => {
				val line = x.split(":( )+")
				val key = line(0)
				val value = line(1).stripSuffix(".")
				val data = "'{\"timestamp\":" + System.currentTimeMillis + ", \"data\": {\"" + key + "\": " + value + "}}'"
				val url = "http://localhost:8080/db/usagestats/vm_stats"
				val curlCmd = Seq("curl", "-H", "'Content-Type: application/json'", "-X", "POST", "-d", data, url)
				println(curlCmd)
				Process(curlCmd).run
				})},
	_ => ())
val proc = Process(vmstatCmd).run(io)
	