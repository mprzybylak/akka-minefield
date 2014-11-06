package pl.mprzybylak.minefields.akka.random.actors

import akka.actor.Actor
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.PrintResultMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.EndProgramMessage
import akka.actor.actorRef2Scala

class PrinterActor extends Actor {

  def receive = {
    case msg:PrintResultMessage => 
      println("obliczona srednia = " + msg.value)
      sender ! EndProgramMessage
  }
  
}