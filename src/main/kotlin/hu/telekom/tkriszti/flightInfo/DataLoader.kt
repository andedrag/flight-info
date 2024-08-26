package hu.telekom.tkriszti.flightInfo

import hu.telekom.tkriszti.flightInfo.dao.CountryRepository
import hu.telekom.tkriszti.flightInfo.dao.FlightRepository
import hu.telekom.tkriszti.flightInfo.dao.PilotRepository
import hu.telekom.tkriszti.flightInfo.model.Country
import hu.telekom.tkriszti.flightInfo.model.Flight
import hu.telekom.tkriszti.flightInfo.model.Pilot
import hu.telekom.tkriszti.flightInfo.model.RiskLevels
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate
import java.time.LocalDate

@Configuration
open class DataLoader(
    private val mongoTemplate: MongoTemplate,
    private val pilotRepository: PilotRepository,
    private val countryRepository: CountryRepository,
    private val flightRepository: FlightRepository
) {

    @Bean
    open fun initDatabase() = CommandLineRunner {
        if (mongoTemplate.findAll(Pilot::class.java).isEmpty() &&
            mongoTemplate.findAll(Country::class.java).isEmpty() &&
            mongoTemplate.findAll(Flight::class.java).isEmpty()
        ) {
            println("Az adatbázis üres, feltöltjük mintaadatokkal...")
            loadData()
        } else {
            println("Az adatbázis már tartalmaz adatokat, nem töltünk be újakat.")
        }
    }

    private fun loadData() {
        val pilots = listOf(
            Pilot("0", 0, name = "KovácsPisti", birthDate = LocalDate.of(1990, 1, 1), phoneNr = "+36201234567", licenseYear = 2010),
            Pilot("1", 1, name = "NagyJános", birthDate = LocalDate.of(1985, 5, 15), phoneNr = "+36309876543", licenseYear = 2008),
            Pilot("2", 2, name = "SzabóÉva", birthDate = LocalDate.of(1992, 9, 30), phoneNr = "+36701122334", licenseYear = 2015)
        )
        pilotRepository.saveAll(pilots)

        val countries = listOf(
            Country("00", name = "Magyarország", currency = "HUF", riskLevel = RiskLevels.LOW),
            Country("01", name = "Németország", currency = "EUR", riskLevel = RiskLevels.LOW),
            Country("02", name = "EgyesültÁllamok", currency = "USD", riskLevel = RiskLevels.MEDIUM)
        )
        countryRepository.saveAll(countries)

        val flights = listOf(
            Flight("000", pilot1Id = pilots[0].id, pilot2Id = pilots[1].id, countryFrom = "Magyarország", countryTo = "Németország", flightTime = 120),
            Flight("001", pilot1Id = pilots[1].id, pilot2Id = pilots[2].id, countryFrom = "Németország", countryTo = "EgyesültÁllamok", flightTime = 540),
            Flight("002", pilot1Id = pilots[2].id, pilot2Id = pilots[0].id, countryFrom = "EgyesültÁllamok", countryTo = "Magyarország", flightTime = 600)
        )
        flightRepository.saveAll(flights)

        println("Mintaadatok sikeresen betöltve!")
    }
}