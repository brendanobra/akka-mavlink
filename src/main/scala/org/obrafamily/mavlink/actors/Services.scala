package org.obrafamily.mavlink.actors

import java.util.logging.Logger

import akka.actor.{ActorRef, ActorSystem}
import com.typesafe.config.Config
import net.ceedubs.ficus.Ficus._
import org.obrafamily.mavlink.actors.io.{MavlinkUdpServer, MavlinkSerialServer, MavlinkTcpServer}
/**
  * Created by brendan on 10/26/16.
  */
trait Services {
  def logger = Logger.getLogger(getClass.getName)
  def getServices(config:Config)(implicit system:ActorSystem):Seq[ActorRef] = {
    logger.info("starting services")
    (config.as[Option[Config]]("MavlinkTcp") match {
      case Some(tcp) =>
        logger.info(s"Starting MavlinkTcpServer")
        val port = tcp.as[Option[Int]]("port").getOrElse(9100)
        val interface = tcp.as[Option[String]]("interface").getOrElse("localhost")
        List(system.actorOf(MavlinkTcpServer.props( port,interface )))
      case None =>
        List()
    }) ++
      (config.as[Option[Config]]("MavlinkUdp") match {
      case Some(udp) =>
        logger.info("starting MavlinkUdpServer")
        val port = udp.as[Option[Int]]("port").getOrElse(9100)
        val interface = udp.as[Option[String]]("interface").getOrElse("localhost")
        List(system.actorOf(MavlinkUdpServer.props(port,interface)))
      case None =>
        List()
    }) ++
    (config.as[Option[Config]]("MavlinkSerial") match {
      case Some(serial) =>
        logger.info("starting MavlinkSerialServer")
        val port = serial.as[Option[String]]("port").getOrElse("/dev/ttyS0")
        List(system.actorOf(MavlinkSerialServer.props(port)))
      case None =>
        List()
    })

  }

}
