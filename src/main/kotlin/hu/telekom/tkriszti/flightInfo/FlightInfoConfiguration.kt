package hu.telekom.tkriszti.flightInfo

import org.hibernate.SessionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class FlightInfoConfiguration {
    @Bean
    open fun createSessionFactory() : SessionFactory {
        val config = org.hibernate.cfg.Configuration()
        config.configure() // load settings from hibernate.cfg.xml
        return config.buildSessionFactory()
    }
}