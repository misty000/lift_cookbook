name := "Lift Cookbook"

version := "0.0.1"

organization := "misty"

scalaVersion := "2.10.0"

resolvers ++= Seq(
    "Local Repository"         at "http://127.0.0.1:8081/nexus/content/repositories/central/",
    "Local Sonatype Release"   at "http://127.0.0.1:8081/nexus/content/repositories/sonatype-release/",
    "Local Sonatype Snapshots" at "http://127.0.0.1:8081/nexus/content/repositories/sonatype-snapshots/"
)

seq(com.github.siasia.WebPlugin.webSettings :_*)

unmanagedResourceDirectories in Test <+= (baseDirectory) { _ / "src/main/webapp" }

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
    val liftVersion = "2.5-RC2"
    Seq(
        "net.liftweb"       %% "lift-webkit"        % liftVersion        % "compile",
        "net.liftmodules"   %% "lift-jquery-module" % (liftVersion + "-2.2"),
        "org.eclipse.jetty" % "jetty-webapp"        % "8.1.7.v20120910"  % "container,test",
        "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
        "org.slf4j"         % "slf4j-api"           % "1.7.2",
        "ch.qos.logback"    % "logback-core"        % "1.0.9",
        "ch.qos.logback"    % "logback-classic"     % "1.0.9",
        "org.specs2"        %% "specs2"             % "1.14"             % "test",
        "com.h2database"    % "h2"                  % "1.3.170"
    )
}

