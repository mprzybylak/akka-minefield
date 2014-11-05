package pl.mprzybylak.minefields.akka.actors

import akka.actor.Actor
import akka.actor.Props

/**
 * Actor extends akka.actor.Actor and have receive method.
 */
class SimpleActor extends Actor {

	println("Simple Actor created");

	def receive = {
	  case "test" 	=> println("test message")
	  
	  // exception in case when we don't handle message
	  case _ 		=> println("unnown type of message")
	}
  
}

/**
 * It is a good practice to holds factory method returning proper configuration of Props instance
 * in Actor's companion object
 */
object SimpleActor {
  def props : Props = Props[SimpleActor]
}