package com.projetoextensao.plataforma_eventos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetoextensao.plataforma_eventos.model.User;
import com.projetoextensao.plataforma_eventos.model.User.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByPapel(Role papel);
    long countByPapel(Role papel);

  
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.eventos WHERE u.email = ?1")
    Optional<User> findByEmailWithEventos(String email);
}