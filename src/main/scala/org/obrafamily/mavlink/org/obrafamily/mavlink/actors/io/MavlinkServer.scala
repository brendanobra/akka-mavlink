package org.obrafamily.mavlink.actors

import akka.actor.{Actor, ActorLogging}
import com.typesafe.config.Config
import org.obrafamily.mavlink.actors.MavlinkServerProtocol.Init


object MavlinkServerProtocol{
  case class Init(config:Config)

}
