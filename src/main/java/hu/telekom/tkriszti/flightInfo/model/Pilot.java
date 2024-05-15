package hu.telekom.tkriszti.flightInfo.model;

import java.time.LocalDate;
import java.util.Date;

public class Pilot {
	private final int Id;
	private String name;
	private final Date birthDate; //LocalDate-et használtam először, de úgy a DAO-ban nem tudom hivatkozni (nincs a resultSetre getLocalDate)
	private int phoneNumber;
	private int licenseYear;

	public Pilot(int Id, String name, Date birthDate, int phoneNumber, int licenseYear) {
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

	public Date getBirthDate() {
		return birthDate;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
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
