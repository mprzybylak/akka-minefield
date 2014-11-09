package pl.mprzybylak.minefields.akka.random.actors

import scala.collection.mutable.ListBuffer
import scala.util.Random

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.Props
import akka.actor.actorRef2Scala
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.CalculateMeanMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.EndProgramMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.GenerateNumberMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.MeanResultMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.PrintResultMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.RandomNumberMessage
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.StartMessage

class MeanActor extends Actor with ActorLogging {

  var randomNumberActor: ActorRef = null;
  var calculateMeanActor: ActorRef = null;
  var printActor: ActorRef = null;

  val numbers: ListBuffer[Int] = ListBuffer.empty
  
  override
  def preStart = {
    log.info("application start")
  }
  
  override
  def postStop = {
    log.info("application stop")
  }

  def receive = {
    case StartMessage => handleStart
    case msg: RandomNumberMessage => handleNumber(msg)
    case msg: MeanResultMessage => printActor ! PrintResultMessage(msg.mean)
    case EndProgramMessage => handleEndProgram
  }

  def handleStart = {
    randomNumberActor = context.actorOf(RandomNumberActor.props(new Random), "generator")
    calculateMeanActor = context.actorOf(Props[CalculateMeanActor], "calculator")
    printActor = context.actorOf(Props[PrinterActor], "printer")
    randomNumberActor ! GenerateNumberMessage
  }

  def handleNumber(msg: RandomNumberMessage) = {
    if (msg.number != 0) {
      numbers += msg.number
      randomNumberActor ! GenerateNumberMessage
    } else {
      calculateMeanActor ! CalculateMeanMessage(numbers.toList)
    }
  }

  def handleEndProgram = {
    context.system.shutdown
  }
}