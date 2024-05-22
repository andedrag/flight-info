package hu.telekom.tkriszti.flightInfo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

		Pilot pilot = null;
		
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pilot WHERE name = ?");
		preparedStatement.setString(1, pilotName);
		
		ResultSet resultSet = preparedStatement.executeQuery();

		if(resultSet.next())
		{
			Date bDay = resultSet.getDate("birthday");
			LocalDate birthDay = bDay.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			pilot = new Pilot( // TODO Kérdés: Miért csinálok belőle minden alkalommal, amikor megtalálja, egy új objektumot? (A mintakódban is így volt)
					resultSet.getInt("Id"),
					resultSet.getString("name"), //TODO Kérdés: Mindegy, hogy ezt írom vagy a paraméterként kapott pilotName-et?
					birthDay,
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

		PreparedStatement preparedStatement = connection.prepareStatement("(SELECT * FROM flights WHERE pilot1id = ? OR pilot2id = ?)");
		preparedStatement.setInt(1, pilotId);
		preparedStatement.setInt(2, pilotId);

		ResultSet resultSet = preparedStatement.executeQuery();

		while(resultSet.next()) {
			Flight flight = new Flight(
					resultSet.getInt("Id"),
					resultSet.getInt("pilot1Id"),
					resultSet.getInt("pilot2Id"),
					resultSet.getInt("countryFrom"),
					resultSet.getInt("countryTo"),
					resultSet.getInt("flightTime")
			);
			flights.add(flight);
		}
		resultSet.close();
		preparedStatement.close();
		return flights;
	}
}































