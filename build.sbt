name := "Scala SBT Template"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

organization := "org.obrafamily"

libraryDependencies ++= {
  	Seq(
           "org.scalactic" %% "scalactic" % "3.0.0",
           "org.scalatest" %% "scalatest" % "3.0.0" % "test",
           "com.typesafe.akka" %% "akka-actor" % "2.4.11",
           "com.typesafe.akka" %% "akka-remote" % "2.4.11",
           "com.github.akileev" %% "akka-serial-io" % "1.0.2",
           "com.typesafe" % "config" % "1.3.1",
          "com.iheart" %% "ficus" % "1.1.3"
  	)
}

enablePlugins(SbtMavlink)
