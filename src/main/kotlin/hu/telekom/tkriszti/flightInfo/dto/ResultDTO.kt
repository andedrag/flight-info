package hu.telekom.tkriszti.flightInfo.dto

import lombok.Getter;
import lombok.Setter

@Getter
@Setter
class ResultDTO (
    private var pilotName: String,
    private var pilotLicenceYear: Int,
    private var flightsByPilot: List<Int>,
    private var totalFlightTime: Int) {

    override fun toString(): String {
        return String.format("$pilotName $pilotLicenceYear Flights: $flightsByPilot $totalFlightTime minutes")
    }
}