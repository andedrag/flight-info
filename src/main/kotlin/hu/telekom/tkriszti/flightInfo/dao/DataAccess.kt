package hu.telekom.tkriszti.flightInfo.dao

import hu.telekom.tkriszti.flightInfo.model.Flight
import hu.telekom.tkriszti.flightInfo.model.Pilot
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.stereotype.Repository
import java.sql.SQLException

@Repository
//@Component
open class DataAccess {
    private var sessionFactory: SessionFactory? = null

//    @Value("\${application.spring.datasource.url}")
//    private val dbUrl: String = ""
//
//    @Value("\${application.spring.datasource.username}")
//    private val dbUsername: String = ""
//
//    @Value("\${application.spring.datasource.password}")
//    private val dbPassword: String = ""
    init {
        val config = Configuration()
        config.configure() // load settings from hibernate.cfg.xml
//        config.setProperty("hibernate.connection.url", dbUrl);
//        config.setProperty("hibernate.connection.username", dbUsername);
//        config.setProperty("hibernate.connection.password", dbPassword);
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