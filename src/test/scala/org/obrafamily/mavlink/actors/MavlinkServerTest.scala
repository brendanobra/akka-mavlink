package org.obrafamily.mavlink.actors

import akka.actor.ActorSystem
import com.typesafe.config.ConfigFactory
import org.obrafamily.mavlink.actors.MavlinkServerProtocol.{Init, InitProcessed}
import akka.testkit.{ImplicitSender, TestActorRef, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

/**
  * Created by brendan on 11/11/16.
  */
class MavlinkServerTest extends MavlinkActorTest {
  val underTest = TestActorRef[MavlinkServer]
  "when MavlinkActor" must {
    "is set a config message" in {
      val config = ConfigFactory.load()
      underTest ! Init(config)
      expectMsg(InitProcessed(config))
    }
  }
}
