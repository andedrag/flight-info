package hu.telekom.tkriszti.flightInfo.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "flights")
public class Flight {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Column(name = "pilot1id")
	private int pilot1Id;
	@Column(name = "pilot2id")
	private int pilot2Id;
	@Column(name = "countryfrom")
	private int countryFrom;
	@Column(name = "countryto")
	private int countryTo;
	private int flightTime; // minutes

	@ManyToMany(mappedBy = "flights")
	private Set<Pilot> pilots;

	public Flight(int Id, int pilot1Id, int pilot2Id, int countryFrom, int countryTo, java.lang.Integer flightTime) {
		this.Id = Id;
		this.pilot1Id = pilot1Id;
		this.pilot2Id = pilot2Id;
		this.countryFrom = countryFrom;
		this.countryTo = countryTo;
		this.flightTime = flightTime;
	}

	public Flight() {
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

