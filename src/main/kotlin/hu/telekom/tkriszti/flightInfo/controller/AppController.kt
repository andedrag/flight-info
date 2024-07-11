package hu.telekom.tkriszti.flightInfo.controller

import hu.telekom.tkriszti.flightInfo.dao.DataAccess
import hu.telekom.tkriszti.flightInfo.dto.ResultDTO
import hu.telekom.tkriszti.flightInfo.service.PilotInfoService
import hu.telekom.tkriszti.flightInfo.service.PilotInfoServiceDbImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.sql.SQLException
import kotlin.jvm.Throws

@Controller
class AppController
    (@Autowired val service: PilotInfoService) {

        @GetMapping("/pilot")
        @Throws(SQLException::class)
        fun showPilotData(model: Model, @RequestParam("name") name: String): String {
            val pilotDtos: Set<ResultDTO?>? = service.getPilotData(name)
            model.addAttribute("pilot", pilotDtos)
            return "pilot.html"
        }
    }
