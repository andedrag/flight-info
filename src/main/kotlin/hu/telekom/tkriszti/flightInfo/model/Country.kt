package hu.telekom.tkriszti.flightInfo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "countries")
data class Country(
    @Id
    val id: String,
    val name: String,
    val currency: String,
    val riskLevel: RiskLevels
) {

    override fun toString(): String {
        return "Country name: $name (currency: $currency, risk level as destination: $riskLevel)"
    }
}