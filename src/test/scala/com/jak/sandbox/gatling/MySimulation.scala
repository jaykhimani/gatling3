package com.jak.sandbox.gatling

import com.typesafe.config.{Config, ConfigFactory}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.{Logger, LoggerFactory}

class MySimulation extends Simulation {
  val log: Logger = LoggerFactory.getLogger(this.getClass.getName)
  val config: Config = ConfigFactory.load("gatling")
  log.debug("dir with substituted value = {}", config.getString("gatling.core.directory.resources"))

  val httpProtocol = http
    .baseUrl("http://computer-database.gatling.io")
    .proxy(Proxy("localhost", 3128))
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
    .doNotTrackHeader("1")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .acceptEncodingHeader("gzip, deflate")
    .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

  val scn = scenario("BasicSimulation")
    .exec(http("request_1")
      .get("/"))
    .pause(5)

  setUp(
    scn.inject(atOnceUsers(1))
  ).protocols(httpProtocol)
}
