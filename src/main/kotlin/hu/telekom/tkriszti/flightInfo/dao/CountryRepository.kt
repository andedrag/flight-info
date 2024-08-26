package hu.telekom.tkriszti.flightInfo.dao

import hu.telekom.tkriszti.flightInfo.model.Country
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface CountryRepository : MongoRepository<Country, String> {
}