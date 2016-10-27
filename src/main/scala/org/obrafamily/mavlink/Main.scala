package org.obrafamily.mavlink

import org.obrafamily.mavlink.actors.{MavlinkActor, MavlinkServer}

import akka.actor.{Props, ActorSystem}
import akka.cluster.pubsub.{DistributedPubSubMediator, DistributedPubSub}
import com.typesafe.config.ConfigFactory
import net.ceedubs.ficus.Ficus._
import org.obrafamily.mavlink.actors.MavlinkServerProtocol.Init


/**
  * Created by brendan on 10/22/16.
  */
object Main extends App{


  override  def main(args: Array[String]): Unit = {
    implicit val config = ConfigFactory.load()
    config.as[String]("app.name")

    implicit val system = ActorSystem( config.as[String]("app.name"), config )
    println(s"Starting with: ${system.name}")
    system.actorOf(Props(new MavlinkServer)) ! Init(config)
    Thread.sleep(10 * 1000)
    import DistributedPubSubMediator.Publish
    // activate the extension
    val mediator = DistributedPubSub(system).mediator
    println(s"publishing")
    mediator ! Publish(MavlinkActor.MavlinkMessages,"boo")
  }
}
