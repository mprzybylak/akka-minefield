package pl.mprzybylak.minefields.akka.random.actors

import akka.actor.Actor
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.GenerateNumberMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.RandomNumberMessage
import scala.util.Random
import akka.actor.actorRef2Scala
import akka.actor.ActorLogging

class RandomNumberActor extends Actor with ActorLogging {
  
  val random = Random

  def receive = {
    case GenerateNumberMessage => 
      val randomNumber = random nextInt 11
      log.info("Generated number = " + randomNumber)
      sender!RandomNumberMessage(randomNumber)
  }
  
}