package hu.telekom.tkriszti.flightInfo.service

import hu.telekom.tkriszti.flightInfo.dto.ResultDTO
import org.springframework.stereotype.Service
import java.sql.SQLException

interface PilotInfoService {
    @Throws(SQLException::class)
    fun getPilotData(pilotName: String): Set<ResultDTO?>?
}