import sbt._
import sbt.Keys._

name := "akka-mavlink"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

organization := "org.obrafamily"

resolvers += Resolver.jcenterRepo

resolvers += Resolver.sonatypeRepo("releases")
val thing = "foo"

val foo = ( thing match {
  case "baz" => "isbaz"
  case "foo" => "$sadfasdfdsf ```` '''' isfoo"
  case _ => "last"

})

commandString := foo

libraryDependencies ++= {
  	Seq(
           "org.scalactic" %% "scalactic" % "3.0.0",
           "org.scalatest" %% "scalatest" % "3.0.0" % "test",
           "com.typesafe.akka" %% "akka-actor" % "2.4.11",
           "com.typesafe.akka" %% "akka-remote" % "2.4.11",
           "com.typesafe.akka" %% "akka-cluster" % "2.4.11",
           "com.typesafe.akka" %% "akka-cluster-tools" % "2.4.11",
           "com.github.akileev" %% "akka-serial-io" % "1.0.2",
           "com.typesafe" % "config" % "1.3.1",
           "com.iheart" %% "ficus" % "1.1.3",
           "com.pi4j" % "pi4j-core" % "1.1"
  	)
}

enablePlugins(SbtMavlink)

enablePlugins(JavaServerAppPackaging)

mavlinkDialect := baseDirectory.value / "mavlink" / "message_definitions" / "v1.0" / "standard.xml"

processMavlinkDialects := {

  mavlinkDialects.value.foreach( dialectFile=> {
    println(s"processing ${dialectFile.getAbsolutePath}")
    mavlinkDialect:= dialectFile
    mavlinkGenerate.value

  })
}

mavlinkDialects := Seq(
  baseDirectory.value / "mavlink" / "message_definitions" / "v1.0" / "standard.xml" ,
  baseDirectory.value / "mavlink" / "message_definitions" / "v1.0" / "minimal.xml",
  baseDirectory.value / "mavlink" / "message_definitions" / "v1.0" / "autoquad.xml"
)

mavlinkTarget := baseDirectory.value / "src_managed"

