package com.algaworks.AprendizadoSpring.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Cidade {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotBlank
    @Column(nullable = false)
    private String nome;

//    @NotNull
//    @ConvertGroup(from = Default.class, to = Groups.EstadoId.class)
//    @Valid
    @ManyToOne //Muitas cidades podem pertencer a um estado
    @JoinColumn(nullable = false)
    private Estado estado;
}
