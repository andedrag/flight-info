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

    override fun toString(): String {
        return "Pilot $id: $name[born $birthDate licensed since $licenseYear (phone:$phoneNr)]"
    }
}