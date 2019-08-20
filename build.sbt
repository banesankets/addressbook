name := """AddressBook"""
organization := "com.acty"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.4"

libraryDependencies += guice
libraryDependencies += "org.webjars" % "jquery" % "3.3.1-1"
libraryDependencies += javaJdbc

libraryDependencies += "commons-dbcp" % "commons-dbcp" % "1.4"
libraryDependencies += "net.sourceforge.jtds" % "jtds" % "1.3.1"
libraryDependencies += "junit" % "junit" % "3.8.1" % "test"

libraryDependencies += jdbc % Test

libraryDependencies += "org.dbunit" % "dbunit" % "2.4.9" % "test"

fork in run := false
fork in Test := false
parallelExecution in Test := false