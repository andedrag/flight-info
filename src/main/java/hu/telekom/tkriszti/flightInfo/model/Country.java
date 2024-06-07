package hu.telekom.tkriszti.flightInfo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final int Id;

    @Column(name = "name")
    private String name;
    @Column(name = "currency")
    private String currency;
    @Column(name = "risklevel")
    private RiskLevels riskLevel;

    public Country(int Id, String name, String currency, RiskLevels riskLevel) {
        this.Id = Id;
        this.name = name;
        this.currency = currency;
        this.riskLevel = riskLevel;
    }

    public int getId() {return Id;}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public RiskLevels getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevels riskLevel) {
        this.riskLevel = riskLevel;
    }

    @Override
    public String toString() {
        return "Country name: " + name + " (currency: " + currency + ", risk level as destination: " + riskLevel + ")";
    }
}
