package com.projetoextensao.plataforma_eventos.controller;

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

import com.projetoextensao.plataforma_eventos.model.Evento;
import com.projetoextensao.plataforma_eventos.repository.EventoRepository;

@Controller
@RequestMapping("/admin/eventos") 
public class AdminEventoController {

    @Autowired
    private EventoRepository eventoRepository;

  
    @GetMapping
    public String listarEventosAdmin(Model model) {
        
        model.addAttribute("eventos", eventoRepository.findAll());
        return "listaEventosAdmin"; 
    }

    @GetMapping({"/novo", "/editar/{id}"})
    public String mostrarFormulario(@PathVariable(required = false) Long id, Model model, CsrfToken token) { 
        Evento evento = new Evento();
        
        if (id != null) {
            Optional<Evento> eventoOptional = eventoRepository.findById(id);
            if (eventoOptional.isPresent()) {
                evento = eventoOptional.get();
                model.addAttribute("acao", "Editar");
            } else {
                
                model.addAttribute("acao", "Novo");
            }
        } else {
            model.addAttribute("acao", "Novo");
        }
        
        model.addAttribute("_csrf", token);
        model.addAttribute("evento", evento);
        return "formEventos"; 
    }


    @PostMapping
    public String salvarEvento(@ModelAttribute Evento evento, RedirectAttributes attributes) {
        
        eventoRepository.save(evento);
        
        String mensagem = (evento.getId() == null) ? "Evento criado com sucesso!" : "Evento atualizado com sucesso!";
        attributes.addFlashAttribute("mensagemSucesso", mensagem);
        return "redirect:/admin/eventos"; 
    }

   
    @PostMapping("/deletar/{id}")
    public String deletarEvento(@PathVariable Long id, RedirectAttributes attributes) {
        eventoRepository.deleteById(id);
        attributes.addFlashAttribute("mensagemSucesso", "Evento excluído com sucesso!");
        return "redirect:/admin/eventos";
    }
}