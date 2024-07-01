package hu.telekom.tkriszti.flightInfo.service

import hu.telekom.tkriszti.flightInfo.dao.DataAccess
import hu.telekom.tkriszti.flightInfo.dto.ResultDTO
import hu.telekom.tkriszti.flightInfo.model.Flight
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException
import java.util.*
import java.util.function.Supplier
import java.util.stream.Collectors

@Service
class PilotInfoServiceDbImpl @Autowired constructor(private val dao: DataAccess) : PilotInfoService {
    @Throws(SQLException::class)
    override fun getPilotData(pilotName: String?): Set<ResultDTO?> {
        val relevantPilotData: MutableSet<ResultDTO?> = HashSet()
        val pilots = dao.getPilotByName(pilotName)
        for (pilot in pilots) {
            val licenseYear = pilot!!.getLicenseYear()
            val flights = dao.getFlightsByPilotId(pilot.getId())
            val flightIds: List<Int> = flights.stream().map { obj: Flight? -> obj!!.getID() }.collect(
                Collectors.toCollection(
                    Supplier { LinkedList() })
            )
            val sumOfFlightTime = getSumOfFlightTime(flights)
            relevantPilotData.add(
                ResultDTO(pilotName, licenseYear, flightIds, sumOfFlightTime)
            )
        }
        return relevantPilotData
    }

    companion object {
        private fun getSumOfFlightTime(flights: Set<Flight?>): Int {
            var sumOfFlightTime = 0
            for (flight in flights) {
                sumOfFlightTime += flight!!.getFlightTime()
            }
            return sumOfFlightTime
        }
    }
}