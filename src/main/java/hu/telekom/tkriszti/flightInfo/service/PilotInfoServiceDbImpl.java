package hu.telekom.tkriszti.flightInfo.service;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import hu.telekom.tkriszti.flightInfo.dao.DataAccess;
import hu.telekom.tkriszti.flightInfo.dto.ResultDTO;
import hu.telekom.tkriszti.flightInfo.model.Flight;
import hu.telekom.tkriszti.flightInfo.model.Pilot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
		List<Integer> flightIds = flights.stream().map(Flight::getID).collect(Collectors.toCollection(LinkedList::new));
		int sumOfFlightTime = getSumOfFlightTime(flights);
		return new ResultDTO(pilotName, licenseYear, flightIds, sumOfFlightTime);
	}

	private static int getSumOfFlightTime(List<Flight> flights) {
		int sumOfFlightTime = 0;
		for (int i = 0; i < flights.size(); i++) {
			Flight currentFlight = flights.get(i);
			sumOfFlightTime += currentFlight.getFlightTime();
		}
		return sumOfFlightTime;
	}
}











