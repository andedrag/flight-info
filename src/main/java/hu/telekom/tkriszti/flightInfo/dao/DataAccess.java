package hu.telekom.tkriszti.flightInfo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import hu.telekom.tkriszti.flightInfo.model.Flight;
import hu.telekom.tkriszti.flightInfo.model.Pilot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class DataAccess {

//	private static final String DB_URL = "jdbc:mysql://localhost:3306/flightdb";
//	private static final String DB_USER = "root";
//	private static final String DB_PWD = "root";
	// private final Connection connection;
	private final SessionFactory sessionFactory;

	public DataAccess()  {
		//this.connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PWD);
		Configuration config = new Configuration();
		config.configure(); // load settings from hibernate.cfg.xml
        sessionFactory = config.buildSessionFactory();
	}
	
//	public void closeDb() throws SQLException {
//		this.connection.close();
//	}

	public Pilot getPilotByName(String pilotName) throws SQLException { // TODO még nincs felkészítve több találatra, pedig név alapján lehet több találat is

//		Pilot pilot = null;
//
//		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM pilots WHERE name = ?");
//		preparedStatement.setString(1, pilotName);
//
//		ResultSet resultSet = preparedStatement.executeQuery();
//
//		if(resultSet.next())
//		{
//			Date bDay = resultSet.getDate("birthdate");
//			LocalDate birthDay = bDay.toLocalDate();
//			pilot = new Pilot(
//					resultSet.getInt("Id"),
//					resultSet.getString("name"),
//					birthDay,
//					resultSet.getString("phoneNr"),
//					resultSet.getInt("licenseYear")
//				);
//		}
//		resultSet.close();
//		preparedStatement.close();
    
    /** Open Session and Begin Transaction */
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		/** Perform SELECT operation */
		//Pilot pilot = session.get(Pilot.class, pilotName);
		String hql = "SELECT * FROM pilots WHERE name = " + pilotName;
		Pilot pilot = (Pilot) session.createQuery(hql).uniqueResult(); // Több találat kezelése! .list()
		/** Execute transaction and Close Session */
		transaction.commit();
		session.close();

		return pilot; // TODO null kezelése
	}

	public List<Flight> getFlightsByPilotId (int pilotId) throws SQLException {

		List<Flight> flights = new ArrayList<>();

//		PreparedStatement preparedStatement = connection.prepareStatement("(SELECT * FROM flights WHERE pilot1id = ? OR pilot2id = ?)");
//		preparedStatement.setInt(1, pilotId);
//		preparedStatement.setInt(2, pilotId);
//
//		ResultSet resultSet = preparedStatement.executeQuery();
//
//		while(resultSet.next()) {
//			Flight flight = new Flight(
//					resultSet.getInt("Id"),
//					resultSet.getInt("pilot1Id"),
//					resultSet.getInt("pilot2Id"),
//					resultSet.getInt("countryFrom"),
//					resultSet.getInt("countryTo"),
//					resultSet.getInt("flightTime")
//			);
//			flights.add(flight);
//		}
//		resultSet.close();
//		preparedStatement.close();

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Flight flight = session.get(Flight.class, pilotId); //TODO hogyan kell Hibernate-ben végigmenni a találatokon? EGyáltalán megadni az egyedi SQL-t?
		flights.add(flight);

		transaction.commit();
		session.close();
		return flights;
	}
}