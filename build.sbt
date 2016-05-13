enablePlugins(ScalaJSPlugin)

name := "Personal_Site"

version := "1.0"

scalatex.SbtPlugin.projectSettings

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "scalatags" % "0.5.4",
  "be.doeraene" %%% "scalajs-jquery" % "0.9.0",
  "com.lihaoyi" %% "scalatags" % "0.5.4",
  "org.pegdown" % "pegdown" % "1.6.0",
  "com.github.japgolly.scalacss" %%% "ext-scalatags" % "0.4.0",
  "eu.henkelmann" % "actuarius_2.10.0" % "0.2.6")

persistLauncher in Compile := true
persistLauncher in Test := false

skip in packageJSDependencies := false