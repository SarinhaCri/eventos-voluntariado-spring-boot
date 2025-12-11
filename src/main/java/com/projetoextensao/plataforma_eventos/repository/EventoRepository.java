package com.projetoextensao.plataforma_eventos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.projetoextensao.plataforma_eventos.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    @Query("SELECT e FROM Evento e LEFT JOIN FETCH e.voluntarios")
    List<Evento> findAllWithVoluntariosEagerly();
}
