package org.obrafamily.mavlink.org.obrafamily.mavlink.actors

import akka.actor.{Props, ActorRef, Actor, ActorLogging}
import akka.util.ByteString
import org.obrafamily.mavlink.org.obrafamily.mavlink.actors.MavlinkMessageProcessor.DataReceived
import org.mavlink._
/**
  * Created by brendan on 10/23/16.
  */
class MavlinkMessageProcessor(requestor:ActorRef) extends Actor with ActorLogging{

  def receive = {
    case DataReceived(data) =>

      log.info(s"received ${data.decodeString("US-ASCII")}")

    case _ =>
  }

}
object MavlinkMessageProcessor {

  def props(requestor:ActorRef) = {
    Props(new MavlinkMessageProcessor(requestor))
  }

  case class DataReceived(data:ByteString)

}
