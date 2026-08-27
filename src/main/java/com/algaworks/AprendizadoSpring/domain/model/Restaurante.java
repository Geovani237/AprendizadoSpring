package com.algaworks.AprendizadoSpring.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@ValorZeroIncluiDescricao(valorField = "taxaFrete",
//        descricaoField = "nome", descricaoObrigatoria = "Frete Grátis")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @NotNull não pode ser null
//    @NotEmpty não pode ser null nem estar empty
//    @NotBlank
    @Column(nullable = false)
    private String nome;

//    @DecimalMin("1")
//    @NotNull
//    @PositiveOrZero
//    @TaxaFrete
//    @Multiplo(numero = 5)
    @Column(name = "taxa_frete", nullable = false)
    private BigDecimal taxaFrete;

    //Many restaurantes podem pertencer a One cozinha
//    @JsonIgnoreProperties("hibernateLazyInitializer")
//    @JsonIgnore
//    @Valid
//    @ConvertGroup(from = Default.class, to = Groups.CozinhaId.class)
//    @NotNull
    @ManyToOne//(fetch = FetchType.LAZY)//Todas as associações ...ToOne usa Eager Loading, que seria um carregamento ansioso/antecipado
    @JoinColumn(name = "cozinha_id", nullable = false)
    private Cozinha cozinha;

    @Embedded
    private Endereco endereco;

    private Boolean ativo = Boolean.TRUE;

    private Boolean aberto = Boolean.FALSE;

    @CreationTimestamp
    @Column(nullable = false)
    private OffsetDateTime dataCadastro;

    @UpdateTimestamp
    @Column(nullable = false)
    private OffsetDateTime dataAtualizacao;

    @ManyToMany//Toda associações que termina com ...ToMany usa a estratégia Lazy Loading por padrão
    @JoinTable(name = "restaurante_forma_pagamento",
    joinColumns = @JoinColumn(name = "restaurante_id"),
    inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private Set<FormaPagamento> formasPagamento = new HashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produtos = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "restaurante_usuario_responsavel",
    joinColumns = @JoinColumn(name = "restaurante_id"),
    inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private Set<Usuario> usuarios = new HashSet<>();

    public void abrir() {
        setAberto(true);
    }

    public void fechar() {
        setAberto(false);
    }

    public void ativar() {
        setAtivo(true);
    }

    public void inativar() {
        setAtivo(false);
    }

    public boolean removerFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().remove(formaPagamento);
    }

    public boolean adicionarFormaPagamento(FormaPagamento formaPagamento) {
        return getFormasPagamento().add(formaPagamento);
    }

    public boolean removerUsuario(Usuario usuario) {
        return getUsuarios().remove(usuario);
    }

    public boolean adicionarUsuario(Usuario usuario) {
        return getUsuarios().add(usuario);
    }


}
