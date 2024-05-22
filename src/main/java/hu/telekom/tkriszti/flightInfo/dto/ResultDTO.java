package hu.telekom.tkriszti.flightInfo.dto;

import hu.telekom.tkriszti.flightInfo.model.Pilot;

import java.util.List;

public class ResultDTO {
	
	private String pilotName;
	private int pilotLicenceYear;
	private List<Integer> flightsByPilot; // TODO Nem hazsnálhatja a Flightot. A service tud mappaleni Model és DTO közöt, de ezek nem hazsnálják egymást
	private int totalFlightTime; // TODO Ne legyen String, maradjon percben megadva. Majd a UI kitalálja, hogyan akarja megjeleníteni

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
		return String.format(pilotName + " " + pilotLicenceYear +"%n%n" + flightsByPilot + "%n%n" + totalFlightTime + "%n%n");
	}
}
