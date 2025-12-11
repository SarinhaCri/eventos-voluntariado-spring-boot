package com.projetoextensao.plataforma_eventos.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.projetoextensao.plataforma_eventos.model.User;
import com.projetoextensao.plataforma_eventos.repository.UserRepository;

@Controller
@RequestMapping("/app") 
public class VoluntarioPerfilController {

    @Autowired
    private UserRepository userRepository;

   
    @GetMapping("/perfil") 
    public String showProfile(Model model, Principal principal) {
        
        if (principal == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não autenticado.");
        }
        
        String email = principal.getName();
        
      
        User voluntario = userRepository.findByEmailWithEventos(email)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário logado não encontrado."));
        
        model.addAttribute("voluntario", voluntario);
        
        return "perfilVoluntario"; 
    }
}