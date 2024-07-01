package hu.telekom.tkriszti.flightInfo.model

import jakarta.persistence.*

@Entity
@Table(name = "countries")
class Country (
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var Id: Int = 0,

    @Column(name = "name")
    private var name: String? = null,

    @Column(name = "currency")
    private var currency: String? = null,

    @Column(name = "risklevel")
    private var riskLevel: RiskLevels? = null){

    fun getId(): Int {
        return Id
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getCurrency(): String? {
        return currency
    }

    fun setCurrency(currency: String?) {
        this.currency = currency
    }

    fun getRiskLevel(): RiskLevels? {
        return riskLevel
    }

    fun setRiskLevel(riskLevel: RiskLevels?) {
        this.riskLevel = riskLevel
    }

    override fun toString(): String {
        return "Country name: $name (currency: $currency, risk level as destination: $riskLevel)"
    }
}