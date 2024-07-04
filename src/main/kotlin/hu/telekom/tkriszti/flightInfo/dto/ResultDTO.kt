package hu.telekom.tkriszti.flightInfo.dto

class ResultDTO (
    private var pilotName: String? = null,
    private var pilotLicenceYear: Int = 0,
    private var flightsByPilot: List<Int>? = null,
    private var totalFlightTime: Int = 0 ) {
    fun getPilotName(): String? {
        return pilotName
    }

    fun getPilotLicenceYear(): Int {
        return pilotLicenceYear
    }

    fun getFlightsByPilot(): List<Int>? {
        return flightsByPilot
    }

    fun getTotalFlightTime(): Int {
        return totalFlightTime
    }

    override fun toString(): String {
        return String.format("$pilotName $pilotLicenceYear Flights: $flightsByPilot $totalFlightTime minutes")
    }
}