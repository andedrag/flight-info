package hu.telekom.tkriszti.flightInfo.dao

import hu.telekom.tkriszti.flightInfo.model.Flight
import hu.telekom.tkriszti.flightInfo.model.Pilot
import org.hibernate.SessionFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.sql.SQLException

@Component
//@Repository
open class DataAccess (@Autowired private val sessionFactory: SessionFactory) {

    @Transactional
    @Throws(SQLException::class)
    fun getPilotByName(pilotName: String): Set<Pilot?> {
        val session = sessionFactory.openSession()
        val query = session.createQuery("FROM Pilot P WHERE P.name = :pilotname")
        query.setParameter("pilotname", pilotName)
        val relevantPilots: HashSet<Pilot?> = HashSet(query.list().filterIsInstance<Pilot>().let { HashSet(it) } )
        return relevantPilots
    }

    @Transactional
    @Throws(SQLException::class)
    fun getFlightsByPilotId(pilotId: Int): Set<Flight?> {
        val session = sessionFactory.openSession()
        val query = session.createQuery("FROM Flight F WHERE pilot1Id = :pilotid OR pilot2Id = :pilotid")
        query.setParameter("pilotid", pilotId)
        val relevantFlights: Set<Flight> = HashSet(query.list().filterIsInstance<Flight>().let { HashSet(it) } )
        return relevantFlights
    }
}