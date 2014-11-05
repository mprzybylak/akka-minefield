package pl.mprzybylak.miefields.akka.gs

object Main {
	 def main(args: Array[String]): Unit = {
	   akka.Main.main(Array(classOf[HelloWorld].getName))
	}
}