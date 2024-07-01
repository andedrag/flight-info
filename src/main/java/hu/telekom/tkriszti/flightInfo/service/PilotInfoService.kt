package hu.telekom.tkriszti.flightInfo.service;

import hu.telekom.tkriszti.flightInfo.dto.ResultDTO;

import java.sql.SQLException;
import java.util.Set;

interface PilotInfoService {
    Set<ResultDTO> getPilotData(String pilotName) throws SQLException;
}
