package hu.telekom.tkriszti.flightInfo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "flights")
data class Flight(
    @Id
    val id: String,
    val pilot1Id: String,
    val pilot2Id: String,
    val countryFrom: String,
    val countryTo: String,
    val flightTime: Int
) {

    override fun toString(): String {
        return "" + id
    }
}