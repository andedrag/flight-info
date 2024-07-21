package hu.telekom.tkriszti.flightInfo

import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class FlightInfoConfiguration {


    @Value("\${spring.datasource.url}")
    lateinit var dbUrl: String

    @Value("\${spring.datasource.username}")
    lateinit var dbUsername: String

    @Value("\${spring.datasource.password}")
    lateinit var dbPassword: String

    @Bean
    open fun createSessionFactory() : SessionFactory {
        val config = org.hibernate.cfg.Configuration()
        config.configure() // load settings from hibernate.cfg.xml
        config.setProperty("hibernate.connection.url", dbUrl)
        config.setProperty("hibernate.connection.username", dbUsername)
        config.setProperty("hibernate.connection.password", dbPassword)
        return config.buildSessionFactory()
    }
}