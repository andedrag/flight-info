package hu.telekom.tkriszti.flightInfo.controller

import hu.telekom.tkriszti.flightInfo.dto.ResultDTO
import hu.telekom.tkriszti.flightInfo.service.PilotInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.SQLException

@RestController
class AppControllerJson
    (@Autowired val service: PilotInfoService) {

        @GetMapping("/pilot/json", produces = [MediaType.APPLICATION_JSON_VALUE])
        @Throws(SQLException::class)
        fun getPilotDataJson(@RequestParam("name") name: String): Set<ResultDTO?>? {
            return service.getPilotData(name)
        }
    }
