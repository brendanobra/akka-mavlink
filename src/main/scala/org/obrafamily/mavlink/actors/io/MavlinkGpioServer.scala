package org.obrafamily.mavlink.actors.io

import akka.actor.{ActorRef, Actor, ActorLogging}
import akka.cluster.pubsub.DistributedPubSubMediator.Subscribe
import org.obrafamily.mavlink.actors.MavlinkActor
import org.obrafamily.mavlink.actors.MavlinkActor._

/**
  * Created by brendan on 10/27/16.
  */
class MavlinkGpioServer extends MavlinkActor{
  log.info(s"starting")
  def rx = {
    case msg =>
      log.info(s"GPIO: got msg: $msg")
    case _ =>
      log.info(s"got something")
  }

  override def subscribe(mediator: ActorRef): Unit = {
    mediator ! Subscribe(MavlinkMessages, self)
  }
}
