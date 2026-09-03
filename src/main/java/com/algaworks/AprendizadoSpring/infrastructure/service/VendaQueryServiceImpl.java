package com.algaworks.AprendizadoSpring.infrastructure.service;

import com.algaworks.AprendizadoSpring.domain.filter.VendaDiariaFilter;
import com.algaworks.AprendizadoSpring.domain.model.Pedido;
import com.algaworks.AprendizadoSpring.domain.model.StatusPedido;
import com.algaworks.AprendizadoSpring.domain.model.dto.VendaDiaria;
import com.algaworks.AprendizadoSpring.domain.service.VendaQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService  {

    @Autowired
    private EntityManager manager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filter) {

        var builder = manager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);
        var root = query.from(Pedido.class);

        var predicates = new ArrayList<Predicate>();

        //Seria o Date do MySQL que é semelhante ao LocalDate
        var functionDateDataCriacao = builder.function(
                "date", Date.class, root.get("dataCriacao"));

        var selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao,//data
                builder.count(root.get("id")),//id do pedido
                builder.sum(root.get("valorTotal")));

        if (filter.getRestauranteId() != null) {
            predicates.add(builder.equal(root.get("restaurante").get("id"), filter.getRestauranteId()));
        }

        if (filter.getDataCriacaoInicio() != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoInicio()));
        }

        if (filter.getDataCriacaoFim() != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filter.getDataCriacaoFim()));
        }

        predicates.add(root.get("status").in(
                StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE
        ));

        query.where(predicates.toArray(new Predicate[0]));
        query.select(selection);
        query.groupBy(functionDateDataCriacao);

        return manager.createQuery(query).getResultList();
    }
}
