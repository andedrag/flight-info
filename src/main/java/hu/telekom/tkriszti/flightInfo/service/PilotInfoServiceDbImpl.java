package hu.telekom.tkriszti.flightInfo.service;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
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
	public Set<ResultDTO> getPilotData(String pilotName) throws SQLException {
		Set<ResultDTO> relevantPilotData = new HashSet<>();
		Set<Pilot> pilots = dao.getPilotByName(pilotName);
		for (Pilot pilot : pilots) {
			int licenseYear = pilot.getLicenseYear();
			Set<Flight> flights = dao.getFlightsByPilotId(pilot.getId());
			List<Integer> flightIds = flights.stream().map(Flight::getID).collect(Collectors.toCollection(LinkedList::new));
			int sumOfFlightTime = getSumOfFlightTime(flights);
			relevantPilotData.add(new ResultDTO(pilotName, licenseYear, flightIds, sumOfFlightTime));
		}
		return relevantPilotData;
	}

	private static int getSumOfFlightTime(Set<Flight> flights) {
		int sumOfFlightTime = 0;
		for (Flight flight : flights) {
			sumOfFlightTime +=flight.getFlightTime();
		}
		return sumOfFlightTime;
	}
}











