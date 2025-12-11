package com.projetoextensao.plataforma_eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.projetoextensao.plataforma_eventos.model.User.Role;
import com.projetoextensao.plataforma_eventos.repository.EventoRepository;
import com.projetoextensao.plataforma_eventos.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {

        long totalEventos = eventoRepository.count();
        long totalVoluntarios = userRepository.countByPapel(Role.USER);
        long totalUsuarios = userRepository.count();

        model.addAttribute("totalEventos", totalEventos);
        model.addAttribute("totalVoluntarios", totalVoluntarios);
        model.addAttribute("totalUsuarios", totalUsuarios);

        return "dashboard";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "acesso-negado";
    }
}
