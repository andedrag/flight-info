package hu.telekom.tkriszti.flightInfo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import hu.telekom.tkriszti.flightInfo.model.Flight;
import hu.telekom.tkriszti.flightInfo.model.Pilot;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event;


@Repository
public class DataAccess {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/flightdb";
	private static final String DB_USER = "root";
	private static final String DB_PWD = "root";
	private final Connection connection;
	
	
	public DataAccess() throws SQLException {
		this.connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PWD);
	}
	
	public void closeDb() throws SQLException {
		this.connection.close();
	}

	public Pilot getPilotByName(String pilotName) throws SQLException { // TODO még nincs felkészítve több találatra, pedig név alapján lehet több találat is
		
		Pilot pilot = null;
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pilot WHERE name = ?");
		preparedStatement.setString(1, pilotName);
		
		ResultSet resultSet = preparedStatement.executeQuery();

		if(resultSet.next())
		{
			pilot = new Pilot( // TODO Kérdés: Miért csinálok belőle minden alkalommal, amikor megtalálja, egy új objektumot? (A mintakódban is így volt)
					resultSet.getInt("Id"),
					resultSet.getString("name"), //TODO Kérdés: Mindegy, hogy ezt írom vagy a paraméterként kapott pilotName-et?
					resultSet.getDate("birthday"),
					resultSet.getInt("phoneNumber"),
					resultSet.getInt("licenseYear")
					);
		}			
		resultSet.close();
		preparedStatement.close();
		return pilot;
	}

	public List<Flight> getFlightsByPilot (String pilotName) throws SQLException {
		//TODO Ez a Service-be való

		List<Flight> flights = null;
		Flight flight = null;
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM flight INNER JOIN pilot ON flight.Pilot1Id=pilot.Id WHERE pilot.name = ?");
		// TODO Mindkét pilotId-t ellenőrizni kellene - hogyan?
		preparedStatement.setString(1, "pilotName");

		ResultSet resultSet = preparedStatement.executeQuery();

		if(resultSet.next()) { // TODO iterálni kell (de while-ban hogyan tudok új változóneveket generálni?)

			//TODO
			flight = new Flight(
					resultSet.getInt("Id"),
					resultSet.getInt("pilot1Id"),
					resultSet.getInt("pilot2Id"),
					resultSet.getString("countryFrom"),
					resultSet.getString("countryTo"),
					resultSet.getInt("flightTime")
					);
			flights.add(flight);
		}

		resultSet.close();
		preparedStatement.close();
		return flights; // TODO esetleges nullokat kezelni
	}

	public String getTotalFlightTime(String pilotName) {
		//TODO Ez a Service-be való

		//TODO
		return "ide jön a total flight time majd";
	}
	
}































