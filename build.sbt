name := """scala-ncurses"""

version := "1.0"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-feature", "-deprecation")

javacOptions ++= Seq("-source", "1.8", "-target", "1.8")


libraryDependencies ++= Seq(
  "com.googlecode.lanterna" % "lanterna" % "3.0.0-beta3"
)

fork in run := true

