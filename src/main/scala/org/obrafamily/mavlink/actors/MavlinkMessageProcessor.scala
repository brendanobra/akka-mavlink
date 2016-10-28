package org.obrafamily.mavlink.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.cluster.pubsub.DistributedPubSubMediator.Subscribe
import akka.util.ByteString
import org.obrafamily.mavlink.MavlinkMessage
import org.obrafamily.mavlink.actors.MavlinkActor._
import org.obrafamily.mavlink.actors.MavlinkMessageProcessor.DataReceived

/**
  * Created by brendan on 10/23/16.
  */
class MavlinkMessageProcessor(requestor:ActorRef) extends MavlinkActor{
  log.info(s"starting")

  def rx = {
    case mavlinkMessage:MavlinkMessage =>
      log.info(s"got $mavlinkMessage")
    case DataReceived(data) =>

      log.info(s"MavlinkMessageProcessor:received ${data.decodeString("US-ASCII")}")


    case unknown =>
      log.info(s"got unknown message: $unknown")

  }

  override def subscribe(mediator: ActorRef): Unit = {
    log.info(s"subscribing")
    mediator ! Subscribe(MavlinkMessages, self)

  }
}
object MavlinkMessageProcessor {


  def props(requestor:ActorRef) = {
    Props(new MavlinkMessageProcessor(requestor))
  }

  case class DataReceived(data:ByteString)

}
