package hu.telekom.tkriszti.flightInfo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import hu.telekom.tkriszti.flightInfo.model.Flight;
import hu.telekom.tkriszti.flightInfo.model.Pilot;
import org.springframework.stereotype.Repository;

@Repository
public class DataAccess {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/flightdb";
	private static final String DB_USER = "root";
	private static final String DB_PWD = "root";
	private final Connection connection;

	// TODO Paraméterek értéke @Value-val jöjjön (application.porperties)
	
	
	public DataAccess() throws SQLException {
		this.connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PWD);
	}
	
	public void closeDb() throws SQLException {
		this.connection.close();
	}

	public Pilot getPilotByName(String pilotName) throws SQLException { // TODO még nincs felkészítve több találatra, pedig név alapján lehet több találat is
		// TODO Service-ben hívjam meg ezt, és akkor lehet az innen kapott ID-t használni

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
					resultSet.getString("phoneNumber"),
					resultSet.getInt("licenseYear")
					);
		}			
		resultSet.close();
		preparedStatement.close();
		return pilot; // TODO null kezelése
	}

	public List<Flight> getFlightsByPilotId (int pilotId) throws SQLException {

		List<Flight> flights = new ArrayList<>();

		PreparedStatement preparedStatement = connection.prepareStatement("(SELECT * FROM flight INNER JOIN pilot ON flight.Pilot1Id=pilot.Id WHERE pilot.Id = ?) UNION (SELECT * FROM flight INNER JOIN pilot ON flight.Pilot2Id=pilot.Id WHERE pilot.Id = ?)");
		preparedStatement.setInt(1, pilotId);
		preparedStatement.setInt(2, pilotId);

		ResultSet resultSet = preparedStatement.executeQuery();

		while(resultSet.next()) {
			Flight flight = new Flight(
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
		return flights;
	}
}































