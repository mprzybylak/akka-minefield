package pl.mprzybylak.minefields.akka.random.actors

import akka.actor.Actor
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.GenerateNumberMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.RandomNumberMessage
import scala.util.Random
import akka.actor.actorRef2Scala

class RandomNumberActor extends Actor {
  
  val random = Random

  def receive = {
    case GenerateNumberMessage => 
      val randomNumber = random nextInt 11
      println("Generated number = " + randomNumber);
      sender!RandomNumberMessage(randomNumber)
  }
  
}