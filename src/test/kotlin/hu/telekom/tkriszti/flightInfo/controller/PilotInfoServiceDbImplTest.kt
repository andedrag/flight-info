package hu.telekom.tkriszti.flightInfo.controller

import hu.telekom.tkriszti.flightInfo.dao.FlightRepository
import hu.telekom.tkriszti.flightInfo.dao.PilotRepository
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

    private val pilotRepo = mock(PilotRepository::class.java)
    private val flightRepo = mock(FlightRepository::class.java)
    private val service = PilotInfoServiceDbImpl(pilotRepo, flightRepo)

    @Test
    fun `should return pilot data if pilot exists`() {
        val pilotName = "KovácsPisti"
        val pilotId = "0"
        val licenseYear = 2010
        val birthDate = LocalDate.of(1990, 1, 1)
        val phoneNr = "+36201234567"
        //val flightTime = 556
        val flight1 = Flight("000", pilotId, "1", "Magyarország", "Németország", 120)
        val flight2 = Flight("002", "2", pilotId, "EgyesültÁllamok", "Magyarország", 600)
        val expectedFlights = setOf(flight1, flight2)
        val expectedPilot = Pilot(pilotId, 0, pilotName, birthDate, phoneNr, licenseYear)
        // val expectedFlight = Flight(1, pilotId, flightTime)
        val expectedResultDTO = ResultDTO(pilotName, licenseYear, listOf("000", "002"), 720)
        val expectedSet = hashSetOf(expectedResultDTO) // ez csak a JUnit miatt kell, mert ha csak 1 találat van, akkor Collectionssingletonként értlemezi, és el fog térni a hashsettől

        `when`(pilotRepo.findByName(pilotName)).thenReturn(setOf(expectedPilot))
        `when`(flightRepo.findByPilot1IdOrPilot2Id(pilotId)).thenReturn(expectedFlights)

        val actualPilotData = service.getPilotData(pilotName)

        assertEquals(expectedSet, actualPilotData)
    }

    @Test
    fun `should return empty set when pilot does not exist`() {
        val pilotName = "Nem Létező Pilot"
        `when`(pilotRepo.findByName(pilotName)).thenReturn(emptySet())
        val actualPilotData = service.getPilotData(pilotName)
        assertTrue(actualPilotData.isEmpty())
    }
}