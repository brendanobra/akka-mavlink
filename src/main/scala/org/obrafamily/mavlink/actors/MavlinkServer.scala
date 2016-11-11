package org.obrafamily.mavlink.actors

import akka.actor.{Actor, ActorLogging, ActorRef}
import akka.cluster.pubsub.DistributedPubSubMediator.Subscribe
import akka.cluster.pubsub.{DistributedPubSub, DistributedPubSubMediator}
import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._
import MavlinkServerProtocol.{Init, InitProcessed}
import org.obrafamily.mavlink.MavlinkPubSubMessages


/**
  * Created by brendan on 10/22/16.
  * Main Service class
  * Sets up IO channels
  */
class MavlinkServer extends MavlinkActor with Services{

  var services:Seq[ActorRef] = _

  def rx  = {
    case Init(init) =>
      val requestor = sender()
      log.info(s"got init")
      val config = init.config
      services = getServices( config.as[Config]("services") )(context.system)
      context.system.actorOf(MavlinkMessageProcessor.props())
      requestor ! InitProcessed(init)

    case msg =>
      log.info(s"Server:got msg: $msg")

  }


  override def subscribe(mediator: ActorRef): Unit = {
    mediator ! Subscribe(MavlinkPubSubMessages.mavlinkMessageInbound,self)
  }

}

object MavlinkServerProtocol{
  case class Init(config:Config)
  case class InitProcessed(config:Config)
}

