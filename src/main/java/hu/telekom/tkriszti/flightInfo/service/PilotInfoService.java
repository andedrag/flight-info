package hu.telekom.tkriszti.flightInfo.service;

import hu.telekom.tkriszti.flightInfo.dto.PilotDTO;
import hu.telekom.tkriszti.flightInfo.model.Flight;
import hu.telekom.tkriszti.flightInfo.model.Pilot;

import java.sql.SQLException;
import java.util.List;

interface PilotInfoService {
    PilotDTO getPilotData(String pilotName) throws SQLException;
}
