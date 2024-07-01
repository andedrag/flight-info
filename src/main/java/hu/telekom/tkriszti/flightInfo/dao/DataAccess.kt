package hu.telekom.tkriszti.flightInfo.dao;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

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
		Configuration config = new Configuration();
		config.configure(); // load settings from hibernate.cfg.xml
        sessionFactory = config.buildSessionFactory();
	}
	
	public Set<Pilot> getPilotByName(String pilotName) throws SQLException {
    	/** Open Session and Begin Transaction */
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		/** Perform SELECT operation */
		Query query = session.createQuery("FROM Pilot P WHERE P.name = :pilotname");
		query.setParameter("pilotname", pilotName);
		Set<Pilot> relevantPilots = new HashSet<>(query.list());
		/** Execute transaction and Close Session */
		transaction.commit();
		session.close();
		return relevantPilots;
	}

	public Set<Flight> getFlightsByPilotId (int pilotId) throws SQLException {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Query query = session.createQuery("FROM Flight F WHERE pilot1Id = :pilotid OR pilot2Id = :pilotid");
		query.setParameter("pilotid", pilotId);
		Set<Flight> relevantFlights = new HashSet<>(query.list());
		transaction.commit();
		session.close();
		return relevantFlights;
	}
}