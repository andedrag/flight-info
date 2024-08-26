package hu.telekom.tkriszti.flightInfo.dto

import lombok.Getter;
import lombok.Setter

@Getter
@Setter
data class ResultDTO (
    val pilotName: String,
    val pilotLicenceYear: Int,
    val flightsByPilot: List<String>,
    val totalFlightTime: Int) {

    override fun toString(): String {
        return String.format("$pilotName $pilotLicenceYear Flights: $flightsByPilot $totalFlightTime minutes")
    }
}