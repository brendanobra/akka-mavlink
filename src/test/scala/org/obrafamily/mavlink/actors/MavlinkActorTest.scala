package org.obrafamily.mavlink.actors

import akka.actor.ActorSystem
import akka.cluster.pubsub.DistributedPubSubMediator.SubscribeAck
import akka.testkit.{ImplicitSender, TestKit, TestActorRef}
import org.scalatest.{BeforeAndAfterAll, WordSpecLike, Matchers, FlatSpec}

/**
  * Created by brendan on 10/30/16.
  */
class MavlinkActorTest extends TestKit(ActorSystem("MySpec")) with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {
  val underTest = TestActorRef[MavlinkServer]
  "when MavlinkActor" must {
    "is set a config message" in {
      expectMsg("bar")
    }
  }
}
