package pl.mprzybylak.minefields.akka.random

object Main {
	def main(args: Array[String]) {
		akka.Main.main(Array(classOf[MeanActor].getName))
	}
}