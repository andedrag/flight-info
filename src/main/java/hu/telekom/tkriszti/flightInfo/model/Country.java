package hu.telekom.tkriszti.flightInfo.model;

public class Country {
    private final int Id;
    private String country; // lehetne enum és szedhetné db-ből
    private String currency; // lehetne enum és szedhetné db-ből
    private Level riskLevel;

    public Country(int Id, String country, String currency, Level riskLevel) {
        this.Id = Id;
        this.country = country;
        this.currency = currency;
        this.riskLevel = riskLevel;
    }

    public int getId() {return Id;}
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Level getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(Level riskLevel) {
        this.riskLevel = riskLevel;
    }

    public enum Level {
        LOWEST,
        LOWER,
        LOW,
        NEUTRAL,
        HIGH,
        HIGHER,
        EXTREME
    }

    @Override
    public String toString() {
        return "Country name: " + country + " (currency: " + currency + ", risk level as destination: " + riskLevel + ")";
    }
}
