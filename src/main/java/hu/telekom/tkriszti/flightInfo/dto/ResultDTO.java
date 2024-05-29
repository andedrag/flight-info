package hu.telekom.tkriszti.flightInfo.dto;

import hu.telekom.tkriszti.flightInfo.model.Pilot;

import java.util.List;

public class ResultDTO {
	
	private String pilotName;
	private int pilotLicenceYear;
	private List<Integer> flightsByPilot;
	private int totalFlightTime;

	public ResultDTO(String pilotName, int pilotLicenceYear, List<Integer> flightsByPilot, int totalFlightTime) {
		this.pilotName = pilotName;
		this.pilotLicenceYear = pilotLicenceYear;
		this.flightsByPilot = flightsByPilot;
		this.totalFlightTime = totalFlightTime;
	}

	public String getPilotName() {
		return pilotName;
	}

	public void setPilotName(String pilotName) {
		this.pilotName = pilotName;
	}

	public int getPilotLicenceYear() {
		return pilotLicenceYear;
	}

	public void setPilotLicenceYear(int pilotLicenceYear) {
		this.pilotLicenceYear = pilotLicenceYear;
	}

	public List<Integer> getFlightsByPilot() {
		return flightsByPilot;
	}

	public void setFlightsByPilot(List<Integer> flightsByPilot) {
		this.flightsByPilot = flightsByPilot;
	}

	public int getTotalFlightTime() {
		return totalFlightTime;
	}

	public void setTotalFlightTime(int totalFlightTime) {
		this.totalFlightTime = totalFlightTime;
	}

	@Override
	public String toString() {
		return String.format(pilotName + " " + pilotLicenceYear +"%n%n" + flightsByPilot +  totalFlightTime );
	}
}
