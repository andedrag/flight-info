package hu.telekom.tkriszti.flightInfo.service;

import hu.telekom.tkriszti.flightInfo.dto.ResultDTO;

import java.sql.SQLException;

interface PilotInfoService {
    ResultDTO getPilotData(String pilotName) throws SQLException;
}
