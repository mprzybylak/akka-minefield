package pl.mprzybylak.minefields.akka.random

import akka.actor.Actor
import akka.actor.Props
import akka.actor.ActorRef
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.GenerateNumberMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.RandomNumberMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.CalculateMeanMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.MeanResultMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.PrintResultMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.EndProgramMessage
import scala.collection.mutable.ListBuffer
import pl.mprzybylak.minefields.akka.random.actors.CalculateMeanActor
import pl.mprzybylak.minefields.akka.random.actors.RandomNumberActor
import pl.mprzybylak.minefields.akka.random.actors.PrinterActor

class MeanActor extends Actor {

  var randomNumberActor:ActorRef = null;
  var calculateMeanActor:ActorRef = null; 
  var printActor:ActorRef = null;
  
  val numbers:ListBuffer[Int] = ListBuffer.empty
  
  override
  def preStart = {
    randomNumberActor = context.actorOf(Props[RandomNumberActor], "generator")
    calculateMeanActor = context.actorOf(Props[CalculateMeanActor], "calculator")
    printActor = context.actorOf(Props[PrinterActor], "printer")
    
    randomNumberActor!GenerateNumberMessage
  }
  
  def receive = {
    case msg:RandomNumberMessage => handleNumber(msg)
    case msg:MeanResultMessage => printActor ! PrintResultMessage(msg.mean)
    case EndProgramMessage => handleEndProgram
  }
  
  def handleNumber(msg:RandomNumberMessage) = {
    if(msg.number != 0) {
      numbers += msg.number
      randomNumberActor!GenerateNumberMessage
    } else { 
      calculateMeanActor!CalculateMeanMessage(numbers.toList)
    }
  }
  
  def handleEndProgram = {
    context.system.shutdown
  }
}