package org.obrafamily.mavlink.actors.io

import akka.actor.{ActorRef}
import akka.cluster.pubsub.DistributedPubSubMediator.Subscribe
import org.obrafamily.mavlink.MavlinkPubSubMessages
import org.obrafamily.mavlink.actors.MavlinkActor


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

   def subscribe(mediator: ActorRef): Unit = {
    mediator ! Subscribe(MavlinkPubSubMessages.mavlinkMessageOutbound, self)
  }
}
