package com.proyecto.prestamos;


public class LoanDTO {

    private String name;
    private double amount;
    private String currency;
    private String idtype;
    private String id;

    LoanDTO(String name, double amount, String currency, String idtype, String id) {
        this.name = name; this.amount=amount; this.currency=currency; this.idtype=idtype; this.id=id;
    }

    public String getName(){
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getIdtype() {
        return idtype;
    }

    public String getId() {
        return id;
    }
}
