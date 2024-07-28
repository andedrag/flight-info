package hu.telekom.tkriszti.flightInfo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "flights")
data class Flight(
    @Id
    val id: String,
    val pilot1Id: String,
    val pilot2Id: String,
    val countryFrom: String,
    val countryTo: String,
    val flightTime: Int
) {
//import jakarta.persistence.*
//
//@Entity
//@Table(name = "flights")
//class Flight (
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private var Id: Int = 0,
//
//    @Column(name = "pilot1id")
//    private var pilot1Id: Int = 0,
//
//    @Column(name = "pilot2id")
//    private var pilot2Id: Int = 0,
//
//    @Column(name = "countryfrom")
//    private var countryFrom: Int = 0,
//
//    @Column(name = "countryto")
//    private var countryTo: Int = 0,
//
//    @Column(name = "flighttime")
//    private var flightTime: Int = 0, // minutes
//
//    @ManyToMany(mappedBy = "relatedFlights")
//    private val pilots: Set<Pilot>? = null){
//
//    fun getID(): Int {
//        return Id
//    }
//
//    fun getPilot1Id(): Int {
//        return pilot1Id
//    }
//
//    fun getPilot2Id(): Int {
//        return pilot2Id
//    }
//
//    fun getCountryFrom(): Int {
//        return countryFrom
//    }
//
//    fun getCountryTo(): Int {
//        return countryTo
//    }
//
//    fun getFlightTime(): Int {
//        return flightTime
//    }

    override fun toString(): String {
        return "" + id
    }
}