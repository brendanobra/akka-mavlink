package org.obrafamily.mavlink

import _root_.org.obrafamily.mavlink.actors.io.MavlinkTcpServer
import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._
import _root_.org.mavlink._
import org.obrafamily.mavlink.actors.io._


/**
  * Created by brendan on 10/22/16.
  */
object Main extends App{


  override  def main(args: Array[String]): Unit = {
    implicit val config = ConfigFactory.load()
    config.as[String]("app.name")

    implicit val system = ActorSystem( config.as[String]("app.name"), config )
    println(s"Starting with: ${system.name}")
    system.actorOf(Props(new MavlinkTcpServer)) ! "bam"
  }
}
