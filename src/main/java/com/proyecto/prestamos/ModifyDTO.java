package com.proyecto.prestamos;


public class ModifyDTO {

    private String name;
    private double amount;
    private String currency;
    private String idtype;
    private String id;
    private String status;

    ModifyDTO(String name, double amount, String currency, String idtpe, String id, String status) {
        this.name = name; this.amount=amount; this.currency=currency;
        this.idtype=idtype; this.id=id; this.status=status;
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

    public String getStatus() {
        return status;
    }
}
