package hu.telekom.tkriszti.flightInfo.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import hu.telekom.tkriszti.flightInfo.dao.DataAccess;
import hu.telekom.tkriszti.flightInfo.dto.PilotDTO;
import hu.telekom.tkriszti.flightInfo.model.Pilot;
import hu.telekom.tkriszti.flightInfo.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class PilotInfoServiceDbImpl implements PilotInfoService {
	
	private final DataAccess dao;
	
	@Autowired
	public PilotInfoServiceDbImpl(DataAccess dao) {
		this.dao = dao;
	}
	// TODO Kérdés: Hogyan tud ide a DAO paraméterként "megérkezni"? A COntrollerben pl. úgy példányosodik, hogy nincs dao paramétere (látszólag)

	@Override
	public PilotDTO getPilotData(String pilotName) throws SQLException {
		Pilot pilot = dao.getPilotByName(pilotName);
		List<Integer> flights = new ArrayList<>();
		dao.getFlightsByPilotId(pilot.getId());
		//TODO végigparse_olni a Flight listát, minden eleméből getId-val kinyerni az int Id,és azt tenni a flights Listbe
		return null;
	}
}











