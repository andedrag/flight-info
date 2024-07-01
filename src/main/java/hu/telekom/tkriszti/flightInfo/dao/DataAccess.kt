package hu.telekom.tkriszti.flightInfo.dao

import hu.telekom.tkriszti.flightInfo.model.Flight
import hu.telekom.tkriszti.flightInfo.model.Pilot
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.springframework.stereotype.Repository
import java.sql.SQLException

@Repository
class DataAccess {

    private var sessionFactory: SessionFactory? = null // A null opció miatt nem lehet contructor paraméter? Ld. vs. Controller

    fun constructor() {
        val config = Configuration()
        config.configure() // load settings from hibernate.cfg.xml
        sessionFactory = config.buildSessionFactory()
    }

    @Throws(SQLException::class)
    fun getPilotByName(pilotName: String?): Set<Pilot?> {
        /** Open Session and Begin Transaction  */
        val session = sessionFactory?.openSession() // !!-t javasolt az IntelliJ
        val transaction = session?.beginTransaction()
        /** Perform SELECT operation  */
        val query = session?.createQuery("FROM Pilot P WHERE P.name = :pilotname")
        query!!.setParameter("pilotname", pilotName)
        val relevantPilots: HashSet<Pilot> = HashSet(query!!.list()?.filterIsInstance<Pilot>()?.let { HashSet(it) } ?: HashSet()) // Ezt lehetne egyszerűbben is?
        /*
        ?.filterIsInstance<Pilot>(): A filterIsInstance<Pilot>() metódussal kiszűrjük a listából azokat az elemeket, amik Pilot típusúak.
        Az eredmény egy új lista lesz, ami csak Pilot objektumokat tartalmaz.
        ?.let { HashSet(it) }: A let blokk segítségével ellenőrizzük, hogy az előző művelet eredménye (a Pilot objektumokat tartalmazó lista) nem null-e.
        Ha nem null, akkor létrehozunk egy új HashSet objektumot a lista elemeiből.
        ?: HashSet(): Az Elvis operátor (?:) segítségével megadunk egy alapértelmezett értéket arra az esetre, ha a query változó null lenne, vagy
        ha a lista nem tartalmazna Pilot objektumokat. Ebben az esetben egy üres HashSet<Pilot> objektumot hozunk létre.
         */
        /** Execute transaction and Close Session  */
        transaction?.commit()
        session.close()
        return relevantPilots
    }

    @Throws(SQLException::class)
    fun getFlightsByPilotId(pilotId: Int): Set<Flight?> {
        val session = sessionFactory!!.openSession()
        val transaction = session.beginTransaction()
        val query = session.createQuery("FROM Flight F WHERE pilot1Id = :pilotid OR pilot2Id = :pilotid")
        query.setParameter("pilotid", pilotId)
        val relevantFlights: Set<Flight> = HashSet(query.list()?.filterIsInstance<Flight>()?.let { HashSet(it) } ?: HashSet())
        transaction.commit()
        session.close()
        return relevantFlights
    }
}