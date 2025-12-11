package com.projetoextensao.plataforma_eventos.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetoextensao.plataforma_eventos.model.User;
import com.projetoextensao.plataforma_eventos.model.User.Role;
import com.projetoextensao.plataforma_eventos.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional
    public void registerNewUser(User user) {
        
        user.setPapel(Role.USER); 
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        userRepository.save(user);
    }
}