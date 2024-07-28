package hu.telekom.tkriszti.flightInfo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "pilots")
data class Pilot(
    @Id
    val id: String,
    val name: String,
    val birthDate: LocalDate,
    val phoneNr : String,
    val licenseYear: Int
) {

//import jakarta.persistence.*
//import java.time.LocalDate
//
//@Entity
//@Table(name = "pilots")
//class Pilot (
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private var Id: Int = 0,
//
//    @Column(name = "name")
//    private var name: String,
//
//    @Column(name = "birthdate")
//    private var birthDate: LocalDate,
//
//    @Column(name = "phonenr")
//    private var phoneNumber: String,
//
//    @Column(name = "licenseyear")
//    private var licenseYear: Int = 0) {
//
//    constructor() : this(0, "", LocalDate.now(), "", 0)
//
//    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
//    @JoinTable(
//        name = "pilots_flights",
//        joinColumns = [JoinColumn(name = "pilot_id")],
//        inverseJoinColumns = [JoinColumn(name = "flight_id")]
//    )
//    private val relatedFlights: List<Flight>? = null
//
//    fun getId(): Int {
//        return Id
//    }
//
//    fun getName(): String {
//        return name
//    }
//
//    fun setName(name: String) {
//        this.name = name
//    }
//
//    fun getBirthDate(): LocalDate {
//        return birthDate
//    }
//
//    fun getPhoneNumber(): String {
//        return phoneNumber
//    }
//
//    fun setPhoneNumber(phoneNumber: String) {
//        this.phoneNumber = phoneNumber
//    }
//
//    fun getLicenseYear(): Int {
//        return licenseYear
//    }
//
//    fun setLicenseYear(licenseYear: Int) {
//        this.licenseYear = licenseYear
//    }

    override fun toString(): String {
        return "Pilot $id: $name[born $birthDate licensed since $licenseYear (phone:$phoneNr)]"
    }
}