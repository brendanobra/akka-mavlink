package org.obrafamily.mavlink.actors.io

import java.net.InetSocketAddress

import akka.actor.{Actor, ActorLogging, ActorRef, Props}

import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator.{Subscribe, Publish}
import akka.io.Tcp._
import akka.io.{IO, Tcp}
import org.obrafamily.mavlink.{MavlinkPubSubMessages, MavlinkMessage}
import org.obrafamily.mavlink.actors.{MavlinkActor, MavlinkMessageProcessor}
import org.obrafamily.mavlink.actors.MavlinkMessageProcessor.DataReceived

/**
  * Created by brendan on 10/22/16.
  */
class MavlinkTcpServer( port: Int,interface:String ) extends Actor with ActorLogging {
  log.info(s"binding to ${interface}:${port}")

  implicit val system = context.system

  IO(Tcp) ! Bind(self, new InetSocketAddress(interface, port))

  def receive = {

    case b@Bound(localAddress) =>
      // do some logging or setup ...
      log.info(s"bound to $port, msg=${b}")
    case CommandFailed(_: Bind) => context stop self

    case c@Connected(remote, local) =>
      val connection = sender()
      log.info(s"connected to remote=${remote} from ${local}")
      val handler = context.actorOf(MavlinkTcpConnectionHandler.props(sender()))
      connection ! Register(handler)

    case _ =>
      log.error("not sure what this message is")
  }

}
/*
This just abstracts TCP connection details... delegates actual message
processing MavlinkMessageProcessor
 */
class MavlinkTcpConnectionHandler(client:ActorRef) extends MavlinkActor {

  import Tcp._

  def rx = {
    case Received(data) =>
      DistributedPubSub(context.system).mediator ! Publish(MavlinkPubSubMessages.mavlinkMessageInbound,MavlinkMessage(data,self,client))

    case PeerClosed =>
      log.info(s"stopping")
      context stop self
    case unknown =>
      log.error(s"received unknown message: $unknown")
  }

   def subscribe(mediator: ActorRef): Unit = {
      mediator ! Subscribe(MavlinkPubSubMessages.mavlinkMessageOutbound,self)
  }
}

object MavlinkTcpConnectionHandler {
  def props(client:ActorRef):Props = {
    Props(new MavlinkTcpConnectionHandler(client))
  }
}

object MavlinkTcpServer {

  def props(port: Int,interface:String = "localhost") = {
    Props(new MavlinkTcpServer(port,interface))
  }

}
