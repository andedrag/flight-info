package hu.telekom.tkriszti.flightInfo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pilots")
public class Pilot {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	@Column(name = "name")
	private String name;
	@Column(name = "birthdate")
	private LocalDate birthDate;
	@Column(name = "phonenr")
	private String phoneNumber;
	@Column(name = "licenseyear")
	private int licenseYear;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "pilots_flights",
			joinColumns = { @JoinColumn(name = "pilot_id") },
			inverseJoinColumns = { @JoinColumn(name = "flight_id") }
	)
	private List<Flight> relatedFlights;

	public Pilot(int Id, String name, LocalDate birthDate, String phoneNumber, int licenseYear) {
		this.Id = Id;
		this.name = name;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.licenseYear = licenseYear;
	}

	public Pilot() {
	}

	public int getId() {
		return Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getLicenseYear() {
		return licenseYear;
	}

	public void setLicenseYear(int licenseYear) {
		this.licenseYear = licenseYear;
	}

	@Override
	public String toString() {
		return "Pilot " + Id + ": " + name + "[born " + birthDate + " licensed since " + licenseYear + " (phone:" + phoneNumber + ")]";
	}
}
