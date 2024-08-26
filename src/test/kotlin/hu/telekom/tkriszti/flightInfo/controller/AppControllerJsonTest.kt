package hu.telekom.tkriszti.flightInfo.controller

import hu.telekom.tkriszti.flightInfo.dto.ResultDTO
import hu.telekom.tkriszti.flightInfo.service.PilotInfoService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(AppControllerJson::class)
class AppControllerJsonTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var pilotInfoService: PilotInfoService

    private val pilotName = "KovacsPeti"
    private val mockPilotData = setOf(
        ResultDTO(
            pilotName = "KovacsPeti",
            pilotLicenceYear = 1919,
            flightsByPilot = listOf("6", "7"),
            totalFlightTime = 444
        )
    )

    @Test
    fun `getPilotData should return proper pilot data`() {

        `when`(pilotInfoService.getPilotData(pilotName)).thenReturn(mockPilotData)

        mockMvc.perform(
            get("/pilot/json")
                .param("name", pilotName)
        )
            .andExpect(status().isOk)
            .andExpect(
                content().json(
                    """
            [
                {
                    "pilotName": "KovacsPeti",
                    "pilotLicenceYear": 1919,
                    "flightsByPilot": [6, 7],
                    "totalFlightTime": 444
                }
            ]
            """.trimIndent()
                )
            )
    }

    @Test
    fun `getPilotData should call pilotInfoService exactly once`() {
        `when`(pilotInfoService.getPilotData(pilotName)).thenReturn(mockPilotData)

        mockMvc.perform(
            get("/pilot/json")
                .param("name", pilotName)
        )
            .andExpect(status().isOk)

        verify(pilotInfoService, times(1)).getPilotData(pilotName)
    }
}
