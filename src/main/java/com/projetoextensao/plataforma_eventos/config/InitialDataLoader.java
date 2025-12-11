package com.projetoextensao.plataforma_eventos.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component; 

import com.projetoextensao.plataforma_eventos.model.Evento;
import com.projetoextensao.plataforma_eventos.model.User;
import com.projetoextensao.plataforma_eventos.model.User.Role; 
import com.projetoextensao.plataforma_eventos.repository.EventoRepository;
import com.projetoextensao.plataforma_eventos.repository.UserRepository;

@Component
public class InitialDataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventoRepository eventoRepository; 

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        
       
        if (userRepository.findByEmail("admin@projetoextensao.com").isEmpty()) {
            
            User admin = new User();
            admin.setNome("Administrador Principal");
            admin.setEmail("admin@projetoextensao.com");
            admin.setPassword(passwordEncoder.encode("123456")); 
            admin.setPapel(Role.ADMIN);
            userRepository.save(admin);
            System.out.println(">>> USUÁRIO ADMIN INICIAL CRIADO: admin@projetoextensao.com / Senha: 123456");

            User voluntario = new User();
            voluntario.setNome("Sara Cristina Viana Rocha");
            voluntario.setEmail("sara@teste.com");
            voluntario.setPassword(passwordEncoder.encode("123456"));
            voluntario.setPapel(Role.USER);
            userRepository.save(voluntario);
            System.out.println(">>> USUÁRIO VOLUNTÁRIO INICIAL CRIADO: sara@teste.com / Senha: 123456");
        }
        
        
        if (eventoRepository.count() == 0) {
            
            Evento evento1 = new Evento();
            evento1.setNome("Mutirão de Limpeza do Rio Tietê");
            evento1.setDescricao("Ajude a retirar lixo e resíduos do trecho urbano do rio.");
            evento1.setLocal("Marginal Pinheiros - Próximo à Ponte Estaiada");
            evento1.setDataHora(LocalDateTime.now().plusDays(7));
            evento1.setCategoria("Meio Ambiente");
            evento1.setVagas(50);
            
          
            
            eventoRepository.save(evento1);
            System.out.println(">>> EVENTO INICIAL CRIADO: " + evento1.getNome());
        }
    }
}