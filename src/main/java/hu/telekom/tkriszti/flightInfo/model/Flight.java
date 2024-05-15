package hu.telekom.tkriszti.flightInfo.model;

public class Flight {
	private final int Id;
	private final int pilot1Id; // TODO Pilot lesz, JPA oldaja fel, hogy majd id legyen (?)
	private final int pilot2Id; // TODO Pilot lesz, JPA oldaja fel, hogy majd id legyen (?)
	private final String countryFrom;
	private final String countryTo;
	private final Integer flightTime;

	public Flight(int Id, int pilot1Id, int pilot2Id, String countryFrom, String countryTo, Integer flightTime) {
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

	public String getCountryFrom() {
		return countryFrom;
	}

	public String getCountryTo() {
		return countryTo;
	}

	public Integer getFlightTime() {
		return flightTime;
	}

	@Override
	public String toString() {
		return "" + Id;
	}
}

