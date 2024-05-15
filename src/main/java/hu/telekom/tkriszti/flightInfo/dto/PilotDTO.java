package hu.telekom.tkriszti.flightInfo.dto;

import hu.telekom.tkriszti.flightInfo.model.Flight;
import hu.telekom.tkriszti.flightInfo.model.Pilot;

import java.util.List;

public class PilotDTO {
	
	private Pilot pilotData;
	private List<Flight> flightsByPilot;
	private String totalFlightTime;

	public PilotDTO(Pilot pilotData, List<Flight> flightsByPilot, String totalFlightTime) {
		this.pilotData = pilotData;
		this.flightsByPilot = flightsByPilot;
		this.totalFlightTime = totalFlightTime;
	}

	public Pilot getPilotData() {
		return pilotData;
	}

	public void setPilotData(Pilot pilotData) {
		this.pilotData = pilotData;
	}

	public List<Flight> getFlightsByPilot() {
		return flightsByPilot;
	}

	public void setFlightsByPilot(List<Flight> flightsByPilot) {
		this.flightsByPilot = flightsByPilot;
	}

	public String getTotalFlightTime() {
		return totalFlightTime;
	}

	public void setTotalFlightTime(String totalFlightTime) {
		this.totalFlightTime = totalFlightTime;
	}

	@Override
	public String toString() {
		return String.format(pilotData +"%n%n" + flightsByPilot + "%n%n" + totalFlightTime + "%n%n");
	}
}
