package org.obrafamily.mavlink

import _root_.org.mavlink.messages.Message
import _root_.org.mavlink.{Packet, Parser}
import akka.util.ByteString

/**
  * Created by brendan on 10/25/16.
  */
class MavlinkParser(data:ByteString) {
  val parser = new Parser(
    (pckt: Packet) => {
      val msg: Message = Message.unpack(pckt.messageId, pckt.payload)
      msg
    },
    (err: Parser.Errors.Error) => {
      sys.error("parse error: " + err)
    }
  )

  def toMessage() = {

  }
}
object  MavlinkParser{
  def apply(data:ByteString) = {
    new MavlinkParser(data)
  }
}
