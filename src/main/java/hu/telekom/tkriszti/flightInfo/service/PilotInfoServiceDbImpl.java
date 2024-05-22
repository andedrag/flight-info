package hu.telekom.tkriszti.flightInfo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hu.telekom.tkriszti.flightInfo.dao.DataAccess;
import hu.telekom.tkriszti.flightInfo.dto.ResultDTO;
import hu.telekom.tkriszti.flightInfo.model.Flight;
import hu.telekom.tkriszti.flightInfo.model.Pilot;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class PilotInfoServiceDbImpl implements PilotInfoService {
	
	private final DataAccess dao;
	
	@Autowired
	public PilotInfoServiceDbImpl(DataAccess dao) {
		this.dao = dao;
	}

	@Override
	public ResultDTO getPilotData(String pilotName) throws SQLException {
		Pilot pilot = dao.getPilotByName(pilotName);
		int licenseYear = pilot.getLicenseYear();

		List<Flight> flights = dao.getFlightsByPilotId(pilot.getId());
		List<Integer> flightIds = new ArrayList<>();
		int sumOfFlightTime = 0;
		for (int i = 0; i < flights.size(); i++) {
			Flight currentFlight = flights.get(i);
			flightIds.add(currentFlight.getID());
			sumOfFlightTime += currentFlight.getFlightTime();
		}

		ResultDTO resultDTO = new ResultDTO(pilotName, licenseYear, flightIds, sumOfFlightTime);
		return resultDTO;
	}
}











