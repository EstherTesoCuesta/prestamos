package com.proyecto.prestamos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    // Web controllers

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/client")
    public String client() {
        return "client";
    }

    @PostMapping("/client/post_loan")
    public String new_loan(@ModelAttribute LoanDTO loanDto, Model model){
        Loan loan;
        String answer;
        try {
            loan = new Loan(loanDto);
            answer="Loan application correctly processed.";
            PrestamosApplication.store_loan_data(loan);
        }
        catch (Exception ex) {
            answer = "Error during loan application: %s.".formatted(ex.getMessage());
        }
        model.addAttribute("answer", answer);
        return "loan_answer";
    }

    @GetMapping("/client/{id}")
    public String client_id() {
        return "client";
    }

    @GetMapping("/manager")
    public String manager(Model model) {
        model.addAttribute("loans", PrestamosApplication.get_loan_data());
        return "manager";
    }

    @GetMapping("/manager/modify_loan/{id}")
    public String modify_loan(Model model, @PathVariable int id) {
        Loan loan = PrestamosApplication.get_loan_data(id);
        if (loan!=null) {
            model.addAttribute("id", id);
            model.addAttribute("loan", loan);
            return "modify_loan";
        }
        else{
            return "index";
        }
    }

    @PostMapping("/client/post_loan/{id}")
    public String new_loan(@ModelAttribute ModifyDTO modifyDto, Model model, @PathVariable int id){
        Loan loan;
        String answer;
        try {
            loan = PrestamosApplication.get_loan_data(id);
            // TODO: update loan
            answer="Loan application correctly modified.";
            PrestamosApplication.store_loan_data(loan);
        }
        catch (Exception ex) {
            answer = "Error during loan application: %s.".formatted(ex.getMessage());
        }
        model.addAttribute("answer", answer);
        return "modify_answer";
    }

    @GetMapping("/system")
    public String system() {
        return "system";
    }

}
