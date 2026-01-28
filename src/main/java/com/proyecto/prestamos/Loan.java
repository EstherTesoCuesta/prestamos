package com.proyecto.prestamos;
import java.time.LocalDate;
import java.util.List;

public class Loan {

    public static sealed abstract class IDType
            permits DNI, Passport {
        protected final String information;
        protected IDType(String information) {
            if (!valid(information)) {
                throw new IllegalArgumentException("Invalid ID: " + information);
            }
            this.information = information;
        }
        protected abstract boolean valid(String information);
        public String toString(){
            return "%s %s".formatted(this.getClass().getSimpleName(), information);
        }
    }

    public static final class DNI extends IDType {
        public DNI(String information) {
            super(information);
        }

        @Override
        protected boolean valid (String information){
            return information.length() == 9;
        }
    }
    public static final class Passport extends IDType {
        public Passport(String information) {
            super(information);
        }

        public boolean valid (String information){
            return information.length() == 9;
        }
    }

    public static IDType IDfrom(String idtype, String information) {
        return switch (idtype.toUpperCase()) {
            case "DNI" -> new DNI(information);
            case "PASSPORT" -> new Passport(information);
            default -> throw new IllegalArgumentException(
                    "No valid id type %s".formatted(idtype)
            );
        };
    }

    public enum Status {PENDING, ACCEPTED, REJECTED, CANCELED;
        @Override
        public String toString() {
            return name().substring(0,1).toUpperCase() + name().substring(1).toLowerCase();
        }
    }

    private String name;
    private double amount;
    private String currency;
    private IDType id;
    private LocalDate creation;
    private Status status;

    Loan(LoanDTO dto){
        this.name=dto.getName(); this.amount=dto.getAmount(); this.currency=dto.getCurrency();
        this.id= IDfrom(dto.getIdtype(),dto.getId());
        this.creation= LocalDate.now(); this.status=Status.PENDING;
    }

    Loan(String name, double amount, String currency, IDType id, LocalDate creation, Status status) {
        this.name = name; this.amount=amount; this.currency=currency;
        this.id=id; this.creation=creation; this.status=status;
    }

    public String getName(){return name;}

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public IDType getId() {
        return id;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public Status getStatus() {
        return status;
    }

    public void updateStatus(String status){
        this.status=Status.valueOf(status.toUpperCase());
    }

    public List<String> next_string_status(){
        return switch (status) {
            case Status.PENDING -> List.of("Accepted", "Rejected");
            case Status.ACCEPTED -> List.of("Canceled");
            default -> List.of();
        };
    }

}
