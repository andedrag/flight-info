package hu.telekom.tkriszti.flightInfo.dto;

import hu.telekom.tkriszti.flightInfo.model.Flight;
import hu.telekom.tkriszti.flightInfo.model.Pilot;

import java.util.List;

public class PilotDTO {
	
	private Pilot pilotData; // TODO Nem hazsnálhatja a PIlotot. A service tud mappaleni Model és DTO közöt, de ezek nem hazsnálják egymást
	private List<Integer> flightsByPilot; // TODO Nem hazsnálhatja a Flightot. A service tud mappaleni Model és DTO közöt, de ezek nem hazsnálják egymást
	private String totalFlightTime; // TODO Ne legyen String, maradjon percben megadva. Majd a UI kitalálja, hogyan akarja megjeleníteni

	public PilotDTO(Pilot pilotData, List<Integer> flightsByPilot, String totalFlightTime) {
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

	public List<Integer> getFlightsByPilot() {
		return flightsByPilot;
	}

	public void setFlightsByPilot(List<Integer> flightsByPilot) {
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
