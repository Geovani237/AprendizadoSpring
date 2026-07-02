package com.algaworks.AprendizadoSpring.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Cidade {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    //Muitas cidades podem pertencer a um estado
    @ManyToOne
    @JoinColumn(nullable = false)
    private Estado estado;
}
