package org.obrafamily.mavlink

import akka.cluster.Cluster

import org.obrafamily.mavlink.actors.{MavlinkServerProtocol, MavlinkServer}

import akka.actor.{Props, ActorSystem}
import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._


/**
  * Created by brendan on 10/22/16.
  */
object Main extends App{


  override  def main(args: Array[String]): Unit = {
    implicit val config = ConfigFactory.load()

    implicit val system = ActorSystem( config.as[String]("app.name"), config )
    /*
    for now, cluster of 1, so just start on first member up...
     */

    Cluster(system).registerOnMemberUp({
      system.actorOf(Props(new MavlinkServer)) ! MavlinkServerProtocol.Init(config)
    })
  }
}
