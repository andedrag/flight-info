package hu.telekom.tkriszti.flightInfo.controller;

import java.sql.SQLException;
import hu.telekom.tkriszti.flightInfo.dto.ResultDTO;
import hu.telekom.tkriszti.flightInfo.service.PilotInfoServiceDbImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
	
	private final PilotInfoServiceDbImpl service;
	
	@Autowired
	public AppController(PilotInfoServiceDbImpl service) {
		super();
		this.service = service;
	}

	// localhost:8080/pilot/kapitanyjanos --> pathvariable-ös annotáció
	// localhost:8080/pilot?name=kapitanyjanos --> @RequestParam
	@GetMapping("/pilot")
	public String showPilotData(
			Model model,
			@RequestParam("name") String name
			) throws SQLException {
		ResultDTO pilotDto = service.getPilotData(name);
		model.addAttribute("pilot", pilotDto);
		return "pilot.html";
	}


}













