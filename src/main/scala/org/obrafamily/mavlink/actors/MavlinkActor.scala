package org.obrafamily.mavlink.actors

import akka.actor.{ActorRef, ActorLogging, Actor}
import akka.cluster.pubsub.{DistributedPubSub, DistributedPubSubMediator}
import akka.cluster.pubsub.DistributedPubSubMediator.{Publish, SubscribeAck, Subscribe}
import akka.event.Logging

/**
  * Created by brendan on 10/27/16.
  */
abstract  class MavlinkActor extends  Actor with  ActorLogging  with PubSub{


  def publish(msg:Any, category:String) = {
    mediator ! Publish(category,msg)
  }

  override def preStart(): Unit = {
    subscribe(mediator)
  }

  def receive:Receive = {
    case ack:SubscribeAck =>
      log.info(s"got subscribeAck=$ack")

    case other =>
      rx(other)
  }

}


