name := "Scala SBT Template"

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.8"

organization := "org.obrafamily"

libraryDependencies ++= {
  	Seq(
           "org.scalactic" %% "scalactic" % "3.0.0",
           "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  	)
}

enablePlugins(SbtMavlink)
