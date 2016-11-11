import com.github.jodersky.mavlink.sbt.SbtMavlink.autoImport._
import sbt._
import sbt.Keys.streams

object MavlinkAkkaBuild extends AutoPlugin {
	object autoImport {
    lazy val awesomeOsPrint = TaskKey[Unit]("awesome-os-print", "Prints all awesome operating systems")
    lazy val awesomeOsList = SettingKey[Seq[String]]("awesome-os-list", "A list of awesome operating systems")
    lazy val commandString = settingKey[String]("the Command to run")
    lazy val mavlinkDialects = settingKey[Seq[File]]("list of mavlink dialect to process")
    lazy val processMavlinkDialects = taskKey[Unit] ("process mavlink dialects defined in mavlinkDialects")



  }



	import autoImport._

}



