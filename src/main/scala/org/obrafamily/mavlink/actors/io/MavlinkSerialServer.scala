package org.obrafamily.mavlink.actors.io

import akka.actor.{ActorRef, Actor, ActorLogging, Props}
import akka.cluster.pubsub.DistributedPubSubMediator.Subscribe
import org.obrafamily.mavlink.actors.MavlinkActor
import org.obrafamily.mavlink.actors.MavlinkActor._

/**
  * Created by brendan on 10/27/16.
  */
class MavlinkSerialServer(port:String) extends  MavlinkActor{
  log.info(s"starting")
  def rx = {
    case msg =>
      log.info(s"Serial: got $msg")
  }

  override def subscribe(mediator: ActorRef): Unit = {
    mediator ! Subscribe(MavlinkMessages, self)
  }
}

object MavlinkSerialServer{
  def props(port:String) = {
    Props(new MavlinkSerialServer(port))
  }
}
