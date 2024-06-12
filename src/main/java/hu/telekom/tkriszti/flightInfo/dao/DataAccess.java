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
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DataAccess {

	private final SessionFactory sessionFactory;

	public DataAccess()  {
		//this.connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PWD);
		Configuration config = new Configuration();
		config.configure(); // load settings from hibernate.cfg.xml
        sessionFactory = config.buildSessionFactory();
	}
	
	public Pilot getPilotByName(String pilotName) throws SQLException { // TODO még nincs felkészítve több találatra, pedig név alapján lehet több találat is

    
    /** Open Session and Begin Transaction */
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		/** Perform SELECT operation */
		//Pilot pilot = session.get(Pilot.class, pilotName);
		Query query = session.createQuery("SELECT * FROM pilots WHERE name = :pilotname");
		query.setParameter("pilotname", pilotName);
		Pilot pilot = (Pilot) query.uniqueResult(); // Több találat kezelése! .list()
		/** Execute transaction and Close Session */
		transaction.commit();
		session.close();

		return pilot; // TODO null kezelése
	}

	public List<Flight> getFlightsByPilotId (int pilotId) throws SQLException {

		List<Flight> flights = new ArrayList<>();

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Flight flight = session.get(Flight.class, pilotId); //TODO hogyan kell Hibernate-ben végigmenni a találatokon? EGyáltalán megadni az egyedi SQL-t?
		flights.add(flight);

		transaction.commit();
		session.close();
		return flights;
	}
}