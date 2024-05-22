package hu.telekom.tkriszti.flightInfo.model;

import java.time.LocalDate;
import java.util.Date;

public class Pilot {
	private final int Id;
	private String name;
	private final LocalDate birthDate;
	private String phoneNumber;
	private int licenseYear;

	public Pilot(int Id, String name, LocalDate birthDate, String phoneNumber, int licenseYear) {
		this.Id = Id;
		this.name = name;
		this.birthDate = birthDate;
		this.phoneNumber = phoneNumber;
		this.licenseYear = licenseYear;
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
