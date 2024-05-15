package hu.telekom.tkriszti.flightInfo.service;

import hu.telekom.tkriszti.flightInfo.model.Flight;
import hu.telekom.tkriszti.flightInfo.model.Pilot;

import java.sql.SQLException;
import java.util.List;

interface PilotInfoService {
    Pilot findPilotByName(String pilotName) throws SQLException;

    List<Flight> listFlightsByPilot(String pilotName) throws SQLException;

    String sumFlightTimeByPilot(String pilotName);
}
