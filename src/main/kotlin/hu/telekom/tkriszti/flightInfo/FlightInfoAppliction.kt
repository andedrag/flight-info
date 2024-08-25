package hu.telekom.tkriszti.flightInfo

import hu.mt.log.rest.EnableSpringRestLogging
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
@EnableSpringRestLogging
@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
open class FlightInfoApplication {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(FlightInfoApplication::class.java, *args)
        }
    }
}