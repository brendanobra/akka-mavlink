package org.obrafamily.mavlink.actors.io

import java.net.InetSocketAddress

import akka.actor.{ActorRef, Props, Actor, ActorLogging}
import akka.io.{IO, Tcp}
import akka.io.Tcp._
import org.obrafamily.mavlink.org.obrafamily.mavlink.actors.MavlinkMessageProcessor
import org.obrafamily.mavlink.org.obrafamily.mavlink.actors.MavlinkMessageProcessor.DataReceived

/**
  * Created by brendan on 10/22/16.
  */
class MavlinkTcpServer(port: Int) extends Actor with ActorLogging {
  log.info(s"binding to port $port")
  implicit val system = context.system
  IO(Tcp) ! Bind(self, new InetSocketAddress("localhost", port))

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
class MavlinkTcpConnectionHandler(client:ActorRef) extends Actor with ActorLogging {

  import Tcp._

  def receive = {
    case Received(data) =>
      context.system.actorOf(MavlinkMessageProcessor.props(client)) ! DataReceived( data )
      client ! Write(data)
    case PeerClosed =>
      context stop self
  }


}
object MavlinkTcpConnectionHandler {
  def props(client:ActorRef):Props = {
    Props(new MavlinkTcpConnectionHandler(client))
  }

}

object MavlinkTcpServer {

  def props(port: Int) = {
    Props(new MavlinkTcpServer(port))
  }

}
