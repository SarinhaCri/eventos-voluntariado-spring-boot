package com.projetoextensao.plataforma_eventos.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetoextensao.plataforma_eventos.model.User;
import com.projetoextensao.plataforma_eventos.model.User.Role;
import com.projetoextensao.plataforma_eventos.repository.UserRepository;

@Controller
@RequestMapping("/admin/voluntarios")
public class AdminVoluntarioController {

    @Autowired
    private UserRepository userRepository;

   
    @GetMapping
    public String listarVoluntarios(Model model) {
        List<User> voluntarios = userRepository.findByPapel(Role.USER);
        model.addAttribute("voluntarios", voluntarios);
        return "listaVoluntariosAdmin";
    }

   
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Long id, Model model, CsrfToken token) {
        Optional<User> voluntarioOptional = userRepository.findById(id);

        if (voluntarioOptional.isEmpty()) {
            return "redirect:/admin/voluntarios";
        }

        model.addAttribute("_csrf", token);
        model.addAttribute("voluntario", voluntarioOptional.get());
        return "formVoluntariosAdmin";
    }

    
    @PostMapping("/salvar")
    public String salvarVoluntario(@ModelAttribute User voluntario, RedirectAttributes attributes) {
        userRepository.save(voluntario);

        attributes.addFlashAttribute("mensagemSucesso",
                "Voluntário " + voluntario.getNome() + " atualizado com sucesso!");
        return "redirect:/admin/voluntarios";
    }


    @PostMapping("/deletar/{id}")
    public String deletarVoluntario(@PathVariable Long id, RedirectAttributes attributes) {
        userRepository.deleteById(id);
        attributes.addFlashAttribute("mensagemSucesso", "Voluntário excluído com sucesso!");
        return "redirect:/admin/voluntarios";
    }
}
