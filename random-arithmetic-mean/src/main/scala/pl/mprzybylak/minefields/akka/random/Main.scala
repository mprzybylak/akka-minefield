package pl.mprzybylak.minefields.akka.random

import akka.actor.ActorSystem
import pl.mprzybylak.minefields.akka.random.actors.CalculateMeanActor
import akka.actor.Props
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.StartMessage

object Main {
	def main(args: Array[String]) {
	  
		val actorSystem = ActorSystem("mean-actor-system");
		
		val meanActor = actorSystem.actorOf(Props[MeanActor]);
		meanActor ! StartMessage;
	  
	}
}