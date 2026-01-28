package com.proyecto.prestamos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.time.LocalDate;

@SpringBootApplication
public class PrestamosApplication {

	// Data simulating Data Base
	private static Map<Integer, Loan> loan_data = new HashMap<Integer, Loan>();

	// Application
	public static void main(String[] args) {
		initialize_loan_data();
		SpringApplication.run(PrestamosApplication.class, args);
	}

	private static void initialize_loan_data(){
		// First client
		Loan.IDType id = new Loan.DNI("12345678A");
		loan_data.put(0, new Loan("Antonio Machado", 500, "EUR", id, LocalDate.of(2025, 9, 23), Loan.Status.CANCELED));
		loan_data.put(1, new Loan("Antonio Machado", 1200, "EUR", id, LocalDate.of(2025, 11, 5), Loan.Status.PENDING));
		// Second client
		id = new Loan.DNI("00000000T");
		loan_data.put(45, new Loan("Almudena Grandes", 830, "USD", id, LocalDate.of(2025, 6, 8), Loan.Status.ACCEPTED));
		loan_data.put(4, new Loan("Almudena Grandes", 2070, "GPB", id, LocalDate.of(2025, 12, 3), Loan.Status.PENDING));
		loan_data.put(4, new Loan("Almudena Grandes", 4000, "GPB", id, LocalDate.of(2025, 5, 30), Loan.Status.ACCEPTED));
		// Third client
		id = new Loan.DNI("23232311W");
		loan_data.put(12, new Loan("Miguel de Cervantes", 7980, "USD", id, LocalDate.of(2025, 6, 8), Loan.Status.CANCELED));
	}

	public static ArrayList<Map.Entry<Integer, Loan>> get_loan_data(){
		return new ArrayList<Map.Entry<Integer, Loan>>(loan_data.entrySet());
	}

	public static Loan get_loan_data(int id){
		return loan_data.get(id);
	}

	public static void store_loan_data(Integer id, Loan loan) {
		loan_data.put(id, loan);
	}

	public static int store_loan_data(Loan loan) {
		int id=0;
		while(loan_data.containsKey(id)){
			id = (int) (Math.random() * 50) + 1;
		}
		loan_data.put(id, loan);
		return id;
	}
}
