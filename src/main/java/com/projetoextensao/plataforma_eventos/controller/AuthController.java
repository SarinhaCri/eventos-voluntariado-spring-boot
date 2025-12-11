package com.projetoextensao.plataforma_eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.projetoextensao.plataforma_eventos.model.User;
import com.projetoextensao.plataforma_eventos.service.AuthService; 

@Controller
public class AuthController {

    @Autowired
    private AuthService authService; 

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

  
    @GetMapping("/signup")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "signup"; 
    }

  
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("user") User user, Model model) {
        
        if (authService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("errorMessage", "Este e-mail já está cadastrado.");
            return "signup"; 
        }
        
       
        authService.registerNewUser(user); 
        return "redirect:/login?registered";
    }
}