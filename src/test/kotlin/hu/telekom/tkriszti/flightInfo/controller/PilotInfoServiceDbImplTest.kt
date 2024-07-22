package hu.telekom.tkriszti.flightInfo.controller

import hu.telekom.tkriszti.flightInfo.dao.DataAccess
import hu.telekom.tkriszti.flightInfo.dto.ResultDTO
import hu.telekom.tkriszti.flightInfo.model.Flight
import hu.telekom.tkriszti.flightInfo.model.Pilot
import hu.telekom.tkriszti.flightInfo.service.PilotInfoServiceDbImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.time.LocalDate

class PilotInfoServiceDbImplTest {

    private val dao = mock(DataAccess::class.java)
    private val service = PilotInfoServiceDbImpl(dao)

    @Test
    fun testGetPilotData() { // létező pilótára adjon vissza pilóta adatot
        val pilotName = "KovacsPisti"
        val pilotId = 4
        val licenseYear = 1982
        val birthDate = LocalDate.of(1956,4,4)
        val phoneNr = "0036704567891"
        //val flightTime = 556
        val flight1 = Flight(1, pilotId, 0, 1, 2, 256)
        val flight2 = Flight(2, pilotId, 0, 3, 4, 300)
        val expectedFlights = setOf(flight1, flight2)
        val expectedPilot = Pilot(pilotId, pilotName, birthDate, phoneNr, licenseYear)
       // val expectedFlight = Flight(1, pilotId, flightTime)
        val expectedResultDTO = ResultDTO(pilotName, licenseYear, listOf(1,2), 556)

        `when`(dao.getPilotByName(pilotName)).thenReturn(setOf(expectedPilot))
        `when`(dao.getFlightsByPilotId(pilotId)).thenReturn(expectedFlights)

        val actualPilotData = service.getPilotData(pilotName)

        assertEquals(setOf(expectedResultDTO), actualPilotData)
        // egyező adatok, mégsem equals. Át kellene írni a ResultDTO equals()-át?
    }
    @Test
    fun testGetPilotDataIfEmpty() {
        val pilotName = "Nem Létező Pilot"
        `when`(dao.getPilotByName(pilotName)).thenReturn(emptySet())
        val actualPilotData = service.getPilotData(pilotName)
        assertTrue(actualPilotData.isEmpty())
    }
}