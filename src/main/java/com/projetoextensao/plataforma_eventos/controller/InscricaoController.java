package com.projetoextensao.plataforma_eventos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetoextensao.plataforma_eventos.service.InscricaoService;

@Controller
@RequestMapping("/app") 
public class InscricaoController {

    @Autowired
    private InscricaoService inscricaoService;

   
    @PostMapping("/inscrever/{eventoId}")
    public String inscreverEmEvento(@PathVariable Long eventoId, RedirectAttributes attributes) {
        
        String redirectUrl = "redirect:/listaEventos"; 

        try {
            inscricaoService.inscreverVoluntario(eventoId);
            attributes.addFlashAttribute("mensagemSucesso", "Inscrição realizada com sucesso! Parabéns!");
        } catch (Exception e) {
            
            attributes.addFlashAttribute("mensagemErro", e.getMessage());
        }

        return redirectUrl; 
    }
    

}