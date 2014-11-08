package pl.mprzybylak.minefields.akka.random.actors

import akka.actor.Actor
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.GenerateNumberMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.RandomNumberMessage
import scala.util.Random
import akka.actor.actorRef2Scala
import akka.actor.ActorLogging
import akka.actor.Props

class RandomNumberActor(random: Random) extends Actor with ActorLogging {
  
  def receive = {
    case GenerateNumberMessage => 
      val randomNumber = random nextInt 11
      log.info("Generated number = " + randomNumber)
      sender!RandomNumberMessage(randomNumber)
  }
  
}

object RandomNumberActor {
  def props(random:Random): Props = Props(new RandomNumberActor(random))
}