package hu.telekom.tkriszti.flightInfo.service

import hu.telekom.tkriszti.flightInfo.dao.FlightRepository
import hu.telekom.tkriszti.flightInfo.dao.PilotRepository
import hu.telekom.tkriszti.flightInfo.dto.ResultDTO
import hu.telekom.tkriszti.flightInfo.model.Flight
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException
import java.util.*
import org.slf4j.LoggerFactory

@Service
class PilotInfoServiceDbImpl(
    @Autowired private val pilotRepo: PilotRepository,
    @Autowired private val flightRepo: FlightRepository
) : PilotInfoService {

    private val logger = LoggerFactory.getLogger(PilotInfoServiceDbImpl::class.java)

    @Throws(SQLException::class)
    override fun getPilotData(pilotName: String): Set<ResultDTO?> {
        val relevantPilotData: MutableSet<ResultDTO?> = HashSet()
        val pilots = pilotRepo.findByName(pilotName)
        for (pilot in pilots) {
            val licenseYear = pilot.licenseYear
            val flights = flightRepo.findByPilot1IdOrPilot2Id(pilot.id)
            val flightIds: List<String> = flights.map { it.id }
            val sumOfFlightTime = getSumOfFlightTime(flights)
            relevantPilotData.add(
                ResultDTO(pilotName, licenseYear, flightIds, sumOfFlightTime)
            )
        }
        logger.info("Returning pilot data based on received pilot name...")
        return relevantPilotData
    }

    companion object {
        private fun getSumOfFlightTime(flights: Set<Flight>): Int {
            return flights.sumOf { it.flightTime }
        }
    }
}