name := """playtime"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  javaWs,
  jdbc
)

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.35"

//comment the following line to run in debug mode
//fork in run := true


//fork in run := true

fork in run := true