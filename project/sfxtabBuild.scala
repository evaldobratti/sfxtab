import sbt._
import Keys._

object SFXTab extends Build {

  val scalafx = "org.scalafx" %% "scalafx" % "1.0.0-M4"

  val testFx = "org.loadui" % "testFx" % "3.1.2" % "test"

  val utest = "com.lihaoyi" %% "utest" % "0.2.3"
  val scalafxml = "scalafxml-dynamic" %% "core" % "0.1"
  lazy val sfxtab =
        Project(
            id = "sfxtab",
            base = file("."),
            settings = commonSettings ++ Seq(
                libraryDependencies ++=
                    Seq(scalafx, testFx, utest, scalafxml)
                )

        )

 
  def commonSettings =
        Defaults.defaultSettings ++ Seq(
            organization := "org.ibratti",
            version := "1.0-SNAPSHOT",
            scalaVersion := "2.10.3",
            publishArtifact in Test := false,
            parallelExecution in Test := false,
            unmanagedBase := baseDirectory.value / "lib",
            publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository"))),
            unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/jfxrt.jar")),
            fork in run := true
        )
}