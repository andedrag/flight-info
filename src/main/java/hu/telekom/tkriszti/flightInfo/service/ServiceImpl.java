package hu.telekom.tkriszti.flightInfo.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import hu.telekom.tkriszti.flightInfo.dao.DataAccess;
import hu.telekom.tkriszti.flightInfo.dto.PilotDTO;
import hu.telekom.tkriszti.flightInfo.model.Pilot;
import hu.telekom.tkriszti.flightInfo.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
	
	private final DataAccess dao;
	
	@Autowired
	public ServiceImpl(DataAccess dao) {
		this.dao = dao;
	}
	// TODO Kérdés: Hogyan tud ide a DAO paraméterként "megérkezni"? A COntrollerben pl. úgy példányosodik, hogy nincs dao paramétere (látszólag)

	public PilotDTO getPilotData(String pilotName) throws SQLException {
		return new PilotDTO(findPilotByName(pilotName), listFlightsByPilot(pilotName), sumFlightTimeByPilot(pilotName));
	}

	@Override
	public Pilot findPilotByName(String pName) throws SQLException {
		return dao.getPilotByName(pName); //TODO Nem jó, duplikálás. Meg kell találni a helyét
	}

	@Override
	public List<Flight> listFlightsByPilot(String pName) throws SQLException {
		return dao.getFlightsByPilot(pName); //TODO Nem jó, duplikálás. Meg kell találni a helyét
	}

	@Override
	public String sumFlightTimeByPilot(String pName) {
		return dao.getTotalFlightTime(pName); //TODO Nem jó, duplikálás. Meg kell találni a helyét
	}
}











