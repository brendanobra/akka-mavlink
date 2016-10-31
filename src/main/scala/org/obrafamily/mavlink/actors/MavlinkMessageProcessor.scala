package org.obrafamily.mavlink.actors

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.cluster.pubsub.DistributedPubSubMediator.{Publish, SubscribeAck, Subscribe}
import akka.util.ByteString
import org.obrafamily.mavlink.{MavlinkParser, MavlinkPubSubMessages, MavlinkMessage}
import org.obrafamily.mavlink.actors.MavlinkMessageProcessor.DataReceived

/**
  * Created by brendan on 10/23/16.
  */
class MavlinkMessageProcessor() extends MavlinkActor{

  log.info(s"starting")



  override def preStart(): Unit = {
    log.info(s"subscribing")
    mediator ! Subscribe(MavlinkPubSubMessages.mavlinkMessageInbound, self)

  }

  def rx = {

    case mavlinkMessage:MavlinkMessage =>
      MavlinkParser(mavlinkMessage.data).parse()
      publish(mavlinkMessage,MavlinkPubSubMessages.mavlinkMessageOutbound)

    case DataReceived(data) =>
      log.info(s"-----------------MavlinkMessageProcessor:received ${data.decodeString("US-ASCII")}")
    case unknown =>

      log.info(s"------------------------got unknown message: $unknown")

  }

  def subscribe(mediator: ActorRef): Unit = {
    log.info(s"subscrbing via $mediator")



  }

}
object MavlinkMessageProcessor {

  def props() = {
    Props(new MavlinkMessageProcessor())
  }

  case class DataReceived(data:ByteString)

}
