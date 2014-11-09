package pl.mprzybylak.minefields.akka.random

import akka.actor.ActorSystem
import pl.mprzybylak.minefields.akka.random.actors.CalculateMeanActor
import akka.actor.Props
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.StartMessage
import pl.mprzybylak.minefields.akka.random.actors.MeanActor
import scala.concurrent.duration._

object Main {
	def main(args: Array[String]) {
		import actorSystem.dispatcher
		val actorSystem = ActorSystem("mean-actor-system");
		val meanActor = actorSystem.actorOf(Props[MeanActor], "meanActor"); 
		actorSystem.scheduler.scheduleOnce(0 seconds, meanActor, StartMessage)
	}
}