package com.algaworks.AprendizadoSpring.infrastructure.repository.spec;

import com.algaworks.AprendizadoSpring.domain.model.Pedido;
import com.algaworks.AprendizadoSpring.domain.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import javax.persistence.criteria.Predicate;

public class PedidoSpecs {

    public static Specification<Pedido> usadoFiltro(PedidoFilter filtro) {
            return (root, query, builder) -> {
//                if (query.getResultType() != Long.class && query.getResultType() != long.class) {
//                    root.fetch("restaurante").fetch("cozinha");
//                    root.fetch("cliente");
//                }
                if (Pedido.class.equals(query.getResultType())) {
                    root.fetch("restaurante").fetch("cozinha");
                    root.fetch("cliente");
                }
                var predicates = new ArrayList<Predicate>();

                if (filtro.getClienteId() != null) {
                    predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
                }

                if (filtro.getRestauranteId() != null) {
                    predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
                }

                if (filtro.getDataCriacaoInicio() != null) {
                    predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"),
                            filtro.getDataCriacaoInicio()));
                }

                if (filtro.getDataCriacaoFim() != null) {
                    predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"),
                            filtro.getDataCriacaoFim()));
                }

                return builder.and(predicates.toArray(new Predicate[0]));
            };
    }
}
