name := "aws-local"

version := "0.1"

scalaVersion := "2.12.8"

val awsSdkVersion = "1.11.552"

libraryDependencies ++= Seq(
  "com.amazonaws" % "aws-java-sdk-s3" % awsSdkVersion,
)
