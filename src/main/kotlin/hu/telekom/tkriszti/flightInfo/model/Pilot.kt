package hu.telekom.tkriszti.flightInfo.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDate

@Document(collection = "pilots")
data class Pilot(
    @Id
    val id: String,
    @Field("id")
    val simpleId: Int,
    val name: String,
    @Field("birthdate")
    val birthDate: LocalDate,
    @Field("phonenr")
    val phoneNr : String,
    @Field("licenseyear")
    val licenseYear: Int
) {

    override fun toString(): String {
        return "Pilot: $name[born $birthDate licensed since $licenseYear (phone:$phoneNr)]"
    }
}