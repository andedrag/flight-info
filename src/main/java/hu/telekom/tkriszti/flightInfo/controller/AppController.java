package hu.telekom.tkriszti.flightInfo.controller;

import java.sql.SQLException;

import hu.telekom.tkriszti.flightInfo.dto.PilotDTO;
import hu.telekom.tkriszti.flightInfo.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppController {
	
	private final ServiceImpl service;
	
	@Autowired
	public AppController(ServiceImpl service) {
		super();
		this.service = service;
	}

	@GetMapping("/pilot/{name}")
	public String showPilotData(
				Model model,
				@RequestParam("name") String name // TODO Kérdés: Hogyan működik a @RequestParam?
			) throws SQLException {
		PilotDTO pilotDto = service.getPilotData(name);
		model.addAttribute("pilot", pilotDto); // TODO átnézni, hogy itt mi tötrténik
		return "user.html"; //TODO Ezt a html-t nem kéne megírni?
	}
	}













