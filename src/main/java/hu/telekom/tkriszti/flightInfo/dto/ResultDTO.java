package hu.telekom.tkriszti.flightInfo.dto;

import hu.telekom.tkriszti.flightInfo.model.Pilot;

import java.util.List;

public final class ResultDTO {
	
	private final String pilotName;
	private final int pilotLicenceYear;
	private final List<Integer> flightsByPilot;
	private final int totalFlightTime;

	public ResultDTO(String pilotName, int pilotLicenceYear, List<Integer> flightsByPilot, int totalFlightTime) {
		this.pilotName = pilotName;
		this.pilotLicenceYear = pilotLicenceYear;
		this.flightsByPilot = flightsByPilot;
		this.totalFlightTime = totalFlightTime;
	}

	public String getPilotName() {
		return pilotName;
	}

	public int getPilotLicenceYear() {
		return pilotLicenceYear;
	}

	public List<Integer> getFlightsByPilot() {
		return flightsByPilot;
	}

	public int getTotalFlightTime() {
		return totalFlightTime;
	}

	@Override
	public String toString() {
		return String.format(pilotName + " " + pilotLicenceYear +"%n%n" + flightsByPilot +  totalFlightTime );
	}
}
