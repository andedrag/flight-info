package hu.telekom.tkriszti.flightInfo.controller

import hu.telekom.tkriszti.flightInfo.dto.ResultDTO
import hu.telekom.tkriszti.flightInfo.service.PilotInfoService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.sql.SQLException

@Controller
class AppControllerHtml
    (@Autowired val service: PilotInfoService) {
    private val logger = LoggerFactory.getLogger(AppControllerHtml::class.java)

        @GetMapping("/pilot")
        @Throws(SQLException::class)
        fun showPilotData(model: Model, @RequestParam("name") name: String): String {
            val pilotDtos: Set<ResultDTO?>? = service.getPilotData(name)
            model.addAttribute("pilot", pilotDtos)
            logger.info("Returning pilot data as html file")
            return "pilot.html"
        }
    }
