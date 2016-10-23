package org.obrafamily.mavlink
import _root_.org.scalatest._
import com.typesafe.config.{Config, ConfigFactory}

import scala.collection.immutable.HashSet
import net.ceedubs.ficus.Ficus._
import scala.collection.JavaConversions._
/**
  * Created by brendan on 10/23/16.
  */
class ConfigTest extends FlatSpec with Matchers {

  val servicesStr =
    """
      |services{
      |  TCP{
      |    port=9100
      |  }
      |  UDP{
      |    port=9100
      |  }
      |  Serial{
      |  }
      |}
    """.stripMargin


  "a config" should "read values from services" in {
      val services = ConfigFactory.parseString(servicesStr).getConfig("services")
      val udp = services.as[Option[Config]]("UDP")
      assert(udp.isDefined)
      val ucp = services.as[Option[Config]]("UCP")
      assert(ucp.isEmpty)

  }


}
