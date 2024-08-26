package hu.telekom.tkriszti.flightInfo.dao

import hu.telekom.tkriszti.flightInfo.model.Flight
import hu.telekom.tkriszti.flightInfo.model.Pilot
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PilotRepository : MongoRepository<Pilot, String> {
    fun findByName(pilotName: String) : Set<Pilot>

      @Query("{ 'pilot1Id': ?0, 'pilot2Id': ?0 }")
    fun findByPilot1IdOrPilot2Id(pilotId: String) : Set<Flight>
}
