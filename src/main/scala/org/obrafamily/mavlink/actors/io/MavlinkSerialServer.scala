package org.obrafamily.mavlink.actors.io

import akka.actor.{ActorRef, Props}
import akka.cluster.pubsub.DistributedPubSubMediator.Subscribe
import org.obrafamily.mavlink.MavlinkPubSubMessages
import org.obrafamily.mavlink.actors.MavlinkActor


/**
  * Created by brendan on 10/27/16.
  */
class MavlinkSerialServer(port:String) extends  MavlinkActor{
  log.info(s"starting")
  def rx = {
    case msg =>
      log.info(s"Serial: got $msg")
  }

   def subscribe(mediator: ActorRef): Unit = {
    mediator ! Subscribe(MavlinkPubSubMessages.mavlinkMessageOutbound, self)
  }
}

object MavlinkSerialServer{
  def props(port:String) = {
    Props(new MavlinkSerialServer(port))
  }
}
