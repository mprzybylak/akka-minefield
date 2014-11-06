package pl.mprzybylak.minefields.akka.random.actors

import akka.actor.Actor
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.CalculateMeanMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.MeanResultMessage
import akka.actor.actorRef2Scala

class CalculateMeanActor extends Actor {

  def receive = {
    case msg:CalculateMeanMessage => sender ! MeanResultMessage(msg.numbers.iterator.sum / msg.numbers.iterator.size)}
}