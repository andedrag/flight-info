package hu.telekom.tkriszti.flightInfo.dao

import hu.telekom.tkriszti.flightInfo.model.Flight
import hu.telekom.tkriszti.flightInfo.model.Pilot
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.sql.SQLException

@Component
//@Repository
open class DataAccess (@Autowired private val sessionFactory: SessionFactory) {

    @Throws(SQLException::class)
    fun getPilotByName(pilotName: String?): Set<Pilot?> {
        /** Open Session and Begin Transaction  */
        val session = sessionFactory.openSession()
        val transaction = session?.beginTransaction()
        /** Perform SELECT operation  */
        val query = session?.createQuery("FROM Pilot P WHERE P.name = :pilotname")
        query!!.setParameter("pilotname", pilotName)
        val relevantPilots: HashSet<Pilot> = HashSet(query.list()?.filterIsInstance<Pilot>()?.let { HashSet(it) } ?: HashSet()) // Ezt lehetne egyszerűbben is?
        /** Execute transaction and Close Session  */
        transaction?.commit()
        session.close()
        return relevantPilots
    }

    @Throws(SQLException::class)
    fun getFlightsByPilotId(pilotId: Int): Set<Flight?> {
        val session = sessionFactory.openSession()
        val transaction = session.beginTransaction()
        val query = session.createQuery("FROM Flight F WHERE pilot1Id = :pilotid OR pilot2Id = :pilotid")
        query.setParameter("pilotid", pilotId)
        val relevantFlights: Set<Flight> = HashSet(query.list()?.filterIsInstance<Flight>()?.let { HashSet(it) } ?: HashSet())
        transaction.commit()
        session.close()
        return relevantFlights
    }
}