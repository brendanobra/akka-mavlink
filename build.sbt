import sbt._
import sbt.Keys._

name := "akka-mavlink"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

organization := "org.obrafamily"

resolvers += Resolver.jcenterRepo

libraryDependencies ++= {
  	Seq(
           "org.scalactic" %% "scalactic" % "3.0.0",
           "org.scalatest" %% "scalatest" % "3.0.0" % "test",
           "com.typesafe.akka" %% "akka-actor" % "2.4.11",
           "com.typesafe.akka" %% "akka-remote" % "2.4.11",
           "com.typesafe.akka" %% "akka-cluster" % "2.4.11",
           "com.github.akileev" %% "akka-serial-io" % "1.0.2",
           "com.typesafe" % "config" % "1.3.1",
           "com.iheart" %% "ficus" % "1.1.3"
  	)
}

enablePlugins(SbtMavlink)

enablePlugins(JavaServerAppPackaging)

mavlinkDialect := baseDirectory.value / "mavlink" / "message_definitions" / "v1.0" / "minimal.xml"

mavlinkTarget := baseDirectory.value / "src_managed"

/*
mappings in Universal += {
  val confFile = buildEnv.value match {
    case BuildEnv.Developement => "dev.conf"
    case BuildEnv.Test => "test.conf"
    case BuildEnv.Stage => "stage.conf"
    case BuildEnv.Production => "prod.conf"
  }
  ((resourceDirectory in Compile).value / confFile) -> "conf/application.conf"
}
*/



