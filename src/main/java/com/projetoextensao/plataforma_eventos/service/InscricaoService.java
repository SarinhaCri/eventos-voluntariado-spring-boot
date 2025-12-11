package com.projetoextensao.plataforma_eventos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import com.projetoextensao.plataforma_eventos.model.Evento;
import com.projetoextensao.plataforma_eventos.model.User;
import com.projetoextensao.plataforma_eventos.repository.EventoRepository;
import com.projetoextensao.plataforma_eventos.repository.UserRepository;

@Service
public class InscricaoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UserRepository userRepository;

    private User getUsuarioLogado() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new Exception("Nenhum usuário logado encontrado.");
        }

        String username = authentication.getName();
        
        return userRepository.findByEmail(username) 
                .orElseThrow(() -> new Exception("Usuário logado não encontrado no banco de dados."));
    }

    @Transactional 
    public void inscreverVoluntario(Long eventoId) throws Exception {
        
        User voluntario = getUsuarioLogado(); 

        Optional<Evento> eventoOpt = eventoRepository.findById(eventoId);
        if (eventoOpt.isEmpty()) {
            throw new Exception("Evento não encontrado.");
        }
        Evento evento = eventoOpt.get();

        int vagasPreenchidas = evento.getVoluntarios().size();

        if (evento.getVagas() != null && vagasPreenchidas >= evento.getVagas()) {
            throw new Exception("Vagas esgotadas para este evento. Tente outro!");
        }

        if (evento.getVoluntarios().contains(voluntario)) {
            throw new Exception("Você já está inscrito neste evento.");
        }

        
        evento.getVoluntarios().add(voluntario);
        voluntario.getEventos().add(evento);

       
        eventoRepository.save(evento);
        userRepository.save(voluntario); 
    }
    
    @Transactional
    public void cancelarInscricao(Long eventoId) throws Exception {
        User voluntario = getUsuarioLogado();

        Optional<Evento> eventoOpt = eventoRepository.findById(eventoId);
        if (eventoOpt.isEmpty()) {
            throw new Exception("Evento não encontrado.");
            }
        Evento evento = eventoOpt.get();
        
        if (!evento.getVoluntarios().contains(voluntario)) {
             throw new Exception("Você não está inscrito neste evento.");
        }

       
        evento.getVoluntarios().remove(voluntario);
        voluntario.getEventos().remove(evento);
        
        
        eventoRepository.save(evento);
        userRepository.save(voluntario); 
    }
}