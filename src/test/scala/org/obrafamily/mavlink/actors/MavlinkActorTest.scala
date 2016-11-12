package org.obrafamily.mavlink.actors

import akka.actor.ActorSystem
import akka.cluster.pubsub.DistributedPubSubMediator.SubscribeAck
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import com.typesafe.config.ConfigFactory
import org.obrafamily.mavlink.actors.MavlinkServerProtocol.{Init, InitProcessed}
import org.scalatest.{BeforeAndAfterAll, FlatSpec, Matchers, WordSpecLike}

/**
  * Created by brendan on 10/30/16.
  */
abstract class MavlinkActorTest extends TestKit(ActorSystem("AkkaMavlinkTest")) with ImplicitSender with WordSpecLike with Matchers with BeforeAndAfterAll {

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }
}
