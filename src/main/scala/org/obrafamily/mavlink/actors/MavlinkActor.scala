package org.obrafamily.mavlink.actors

import akka.actor.{ ActorLogging, Actor}
import akka.cluster.pubsub.DistributedPubSubMediator.{Publish, SubscribeAck}

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


