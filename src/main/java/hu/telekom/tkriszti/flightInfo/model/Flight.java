package hu.telekom.tkriszti.flightInfo.model;

public class Flight {
	private final int Id;
	private final int pilot1Id;
	private final int pilot2Id;
	private final int countryFrom;
	private final int countryTo;
	private final int flightTime; // minutes

	public Flight(int Id, int pilot1Id, int pilot2Id, int countryFrom, int countryTo, java.lang.Integer flightTime) {
		this.Id = Id;
		this.pilot1Id = pilot1Id;
		this.pilot2Id = pilot2Id;
		this.countryFrom = countryFrom;
		this.countryTo = countryTo;
		this.flightTime = flightTime;
	}

	public int getID() {
		return Id;
	}

	public int getPilot1Id() {
		return pilot1Id;
	}

	public int getPilot2Id() {
		return pilot2Id;
	}

	public int getCountryFrom() {
		return countryFrom;
	}

	public int getCountryTo() {
		return countryTo;
	}

	public java.lang.Integer getFlightTime() {
		return flightTime;
	}

	@Override
	public String toString() {
		return "" + Id;
	}
}

