package com.projetoextensao.plataforma_eventos.model;

import java.util.HashSet;
import java.util.Set; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter 
@Setter
@NoArgsConstructor 
@AllArgsConstructor 

@EqualsAndHashCode(exclude = {"eventos"}, callSuper = false) 
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email; 

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String password; 
    
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role papel;

    
    @ManyToMany(mappedBy = "voluntarios")
    @ToString.Exclude 
    private Set<Evento> eventos = new HashSet<>(); 

    public enum Role {
        ADMIN,
        USER
    }
}