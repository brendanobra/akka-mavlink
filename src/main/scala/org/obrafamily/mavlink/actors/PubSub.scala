package org.obrafamily.mavlink.actors

import akka.actor.{Actor, ActorRef}
import akka.cluster.pubsub.DistributedPubSub

/**
  * Created by brendan on 10/30/16.
  */
trait PubSub extends  Actor{
   val pubSub = DistributedPubSub( context.system )
   val mediator = pubSub.mediator
   def rx:Receive
   def subscribe(mediator:ActorRef)
}
