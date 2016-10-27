package org.obrafamily.mavlink.actors

import akka.actor.{ActorRef, ActorLogging, Actor}
import akka.cluster.pubsub.{DistributedPubSub, DistributedPubSubMediator}
import akka.cluster.pubsub.DistributedPubSubMediator.{SubscribeAck, Subscribe}
import org.obrafamily.mavlink.actors.MavlinkActor._
/**
  * Created by brendan on 10/27/16.
  */
abstract class MavlinkActor extends  Actor with  ActorLogging{
  import DistributedPubSubMediator.Subscribe

  val mediator = DistributedPubSub(context.system).mediator
  subscribe(mediator)
  mediator ! Subscribe(MavlinkMessages, self)

  def receive:Receive = {
    case ack:SubscribeAck =>
      log.info(s"subscribed with ack=$ack")
      rx(ack)
    case other =>

      rx(other)
  }

  def rx:Receive

  def subscribe(mediator:ActorRef)
}

object  MavlinkActor {
  val MavlinkMessages="mavlinkMessages"
}