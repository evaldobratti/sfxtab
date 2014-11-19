import sbt._
import Keys._

object SfxutilsBuild extends Build {

  val testFx = "org.loadui" % "testFx" % "3.1.2" % "test"

  val junit = "junit" % "junit" % "4.10" //testFx only works with junit

  val scalaMock = "org.scalamock" %% "scalamock-scalatest-support" % "3.2-RC1" % "test"

  val scalaXml = "org.scala-lang.modules" %% "scala-xml" % "1.0.1" % "test"

  lazy val sfxtab =
        Project(
            id = "sfxutils",
            base = file("."),
            settings = commonSettings ++ Seq(
                libraryDependencies ++=
                    Seq(testFx, scalaMock, junit, scalaXml)
                )

        )

 
  def commonSettings =
        Defaults.defaultSettings ++ Seq(
            organization := "org.ibratti",
            version := "1.0-SNAPSHOT",
            scalaVersion := "2.11.2",
            publishArtifact in Test := false,
            parallelExecution in Test := false,
            unmanagedBase := baseDirectory.value / "lib",
            unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar")),
            fork in run := true

        )
}