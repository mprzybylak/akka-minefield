package pl.mprzybylak.minefields.akka.random.actors

import akka.actor.Actor
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.PrintResultMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.EndProgramMessage
import akka.actor.actorRef2Scala
import akka.actor.ActorLogging

class PrinterActor extends Actor with ActorLogging {

  def receive = {
    case msg:PrintResultMessage => 
      log.info("obliczona srednia = " + msg.value)
      sender ! EndProgramMessage
  }
  
}