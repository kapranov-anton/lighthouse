name := """scala-ncurses"""

version := "1.0"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-feature", "-deprecation")

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")


libraryDependencies ++= Seq(
  "com.googlecode.lanterna" % "lanterna" % "3.0.0-beta3"
, "org.scalactic" %% "scalactic" % "3.0.0"
, "org.scalatest" %% "scalatest" % "3.0.0" % "test"
)

fork in run := true

