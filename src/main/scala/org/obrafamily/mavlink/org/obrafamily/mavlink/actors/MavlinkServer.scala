package org.obrafamily.mavlink.org.obrafamily.mavlink.actors

import akka.actor.{ActorRef, Actor, ActorLogging}
import com.typesafe.config.Config
import org.obrafamily.mavlink.actors.MavlinkServerProtocol.Init
import net.ceedubs.ficus.Ficus._
import org.obrafamily.mavlink.actors.io.MavlinkTcpServer
import scala.collection.JavaConversions._
/**
  * Created by brendan on 10/22/16.
  */
class MavlinkServer extends Actor with ActorLogging{

  var services:Seq[ActorRef] = _

  def receive  = {
    case Init(init) =>
      log.info(s"got init")
      val config = init.config
      services = getServices( config.as[Config]("services") )

  }

  def getServices(config:Config):Seq[ActorRef] = {
    config.as[Option[Config]]("TCP") match {
      case Some(tcp) =>
        val port = tcp.as[Option[Int]]("port").getOrElse(9100)
        List(context.system.actorOf(MavlinkTcpServer.props(port)))
      case None =>
        List()
    }
  }


}
