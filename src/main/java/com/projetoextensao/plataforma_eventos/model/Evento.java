package com.projetoextensao.plataforma_eventos.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set; 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter; 
import lombok.ToString;

@Entity
@Table(name = "eventos")
@Getter 
@Setter 
@NoArgsConstructor 
@AllArgsConstructor 

@EqualsAndHashCode(exclude = {"voluntarios"}, callSuper = false) 
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Lob 
    private String descricao;

    @Column(nullable = false)
    private String local;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    private String categoria;
    
    private String imageUrl; 
    
    private Integer vagas;

    
    @ManyToMany
    @JoinTable(
        name = "inscricoes",
        joinColumns = @JoinColumn(name = "evento_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id") 
    )
    @ToString.Exclude 
    private Set<User> voluntarios = new HashSet<>(); 
}