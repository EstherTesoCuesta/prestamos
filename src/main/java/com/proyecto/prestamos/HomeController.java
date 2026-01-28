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
            model.addAttribute("status_list", loan.next_string_status());
            return "modify_loan";
        }
        else{
            return "index";
        }
    }

    @PostMapping("/manager/status_loan/{id}")
    public String status_loan(Model model,  @RequestParam(required=false) String status, @PathVariable int id){
        Loan loan;
        String answer;
        try {
            if(status!=null) {
                loan = PrestamosApplication.get_loan_data(id);
                loan.updateStatus(status);
                answer = "Loan application correctly modified.";
                //PrestamosApplication.store_loan_data(loan);
            }
            else{
                answer="Status was not changed";
            }
        }
        catch (Exception ex) {
            answer = "Error during loan application: %s.".formatted(ex.getMessage());
        }
        model.addAttribute("answer", answer);
        return "modify_answer";
    }

    @GetMapping("/system")
    public String system(Model model) {
        model.addAttribute("loans", PrestamosApplication.get_loan_data());
        return "system";
    }

}
