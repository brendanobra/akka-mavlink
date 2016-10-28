package org.obrafamily.mavlink

import akka.actor.ActorRef
import akka.util.ByteString

/**
  * Created by brendan on 10/27/16.
  */
case class MavlinkMessage(data:ByteString,requestor:ActorRef,endpoint:ActorRef)
