resolvers += Resolver.bintrayRepo("jodersky","sbt-plugins")

resolvers += Resolver.bintrayRepo("jodersky","maven")

resolvers +=DefaultMavenRepository

addSbtPlugin("com.github.jodersky" %% "sbt-mavlink" % "0.7.0")

addSbtPlugin("io.spray" % "sbt-revolver" % "0.8.0")

addSbtPlugin("com.typesafe.sbt" % "sbt-native-packager" % "1.1.4")
