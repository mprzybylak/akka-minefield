package pl.mprzybylak.minefields.akka.random.actors

import akka.testkit.TestKit
import akka.actor.ActorSystem
import org.scalatest.WordSpecLike
import org.scalatest.matchers.MustMatchers
import org.scalatest.BeforeAndAfterAll
import akka.testkit.TestActorRef
import pl.mprzybylak.minefields.akka.random.messages.MeanMessage.RandomNumberMessage
import scala.util.Random
import com.typesafe.config.ConfigFactory
import akka.testkit.EventFilter

class RandomNumberActorTest extends TestKit(ActorSystem("mean-actor-system", ConfigFactory.parseString("""akka.loggers = ["akka.testkit.TestEventListener"]""")))
  with WordSpecLike
  with MustMatchers
  with BeforeAndAfterAll {

  "An actor" must {
    "genreate random number" in {
      val ref = TestActorRef(RandomNumberActor.props(new Random {
        override def nextInt(n: Int): Int = {
          return 10
        }
      }))

      ref ! RandomNumberMessage;
    }

    "generate logs" in {
      val ref = TestActorRef(RandomNumberActor.props(new Random {
        override def nextInt(n: Int): Int = {
          return 10
        }
      }))

      EventFilter.info(pattern = "*", occurrences = 1) intercept {
        ref ! RandomNumberMessage;
      }
    }
  }

}