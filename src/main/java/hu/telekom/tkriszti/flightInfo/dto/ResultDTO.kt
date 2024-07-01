package hu.telekom.tkriszti.flightInfo.dto

class ResultDTO {
    private var pilotName: String? = null
    private var pilotLicenceYear = 0
    private var flightsByPilot: List<Int>? = null
    private var totalFlightTime = 0

    fun ResultDTO(pilotName: String?, pilotLicenceYear: Int, flightsByPilot: List<Int>?, totalFlightTime: Int) {
        this.pilotName = pilotName
        this.pilotLicenceYear = pilotLicenceYear
        this.flightsByPilot = flightsByPilot
        this.totalFlightTime = totalFlightTime
    }

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