package org.obrafamily.mavlink.actors.io

import akka.actor.{Actor, ActorLogging}

/**
  * Created by brendan on 10/22/16.
  */
class MavlinkTcpServer extends Actor with ActorLogging{

  def receive = {
    case _ => log.info("yoyo")
  }
}
