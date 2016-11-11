package org.obrafamily.mavlink.actors.io

import akka.actor.{Actor, ActorLogging, Props}

/**
  * Created by brendan on 10/27/16.
  */
class MavlinkUdpServer(port:Int,interface:String) extends  Actor with ActorLogging{
  log.info(s"starting")
  def receive = {
    case _ =>
  }
}


object MavlinkUdpServer{
  def props(port:Int, interface:String) = {
    Props(new MavlinkUdpServer(port,interface))
  }
}
