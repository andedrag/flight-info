package hu.telekom.tkriszti.flightInfo.dto

import lombok.Getter;
import lombok.Setter

@Getter
@Setter
class ResultDTO (
    var pilotName: String,
    var pilotLicenceYear: Int,
    var flightsByPilot: List<String>,
    var totalFlightTime: Int) {

    override fun toString(): String {
        return String.format("$pilotName $pilotLicenceYear Flights: $flightsByPilot $totalFlightTime minutes")
    }
}