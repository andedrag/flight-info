package hu.telekom.tkriszti.flightInfo.dao

import hu.telekom.tkriszti.flightInfo.model.Flight
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : MongoRepository<Flight, String> {
    @Query("{ '\$or': [{ 'pilot1Id': ?0 }, { 'pilot2Id': ?0 }] }")
    fun findByPilot1IdOrPilot2Id(pilotId: String) : Set<Flight>
}