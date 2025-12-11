package com.projetoextensao.plataforma_eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.projetoextensao.plataforma_eventos.repository.EventoRepository;

@Controller
public class EventoPublicoController {

    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping({"/", "/listaEventos"})
    public String listarEventos(Model model) {
       
        model.addAttribute("eventos", eventoRepository.findAllWithVoluntariosEagerly());
        return "listaEventos";
    }
}
