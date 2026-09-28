package com.algaworks.AprendizadoSpring.api.controller;

import com.algaworks.AprendizadoSpring.api.assembler.PedidoModelAssembler;
import com.algaworks.AprendizadoSpring.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.AprendizadoSpring.api.disassembler.PedidoInputDisassembler;
import com.algaworks.AprendizadoSpring.api.model.PedidoModel;
import com.algaworks.AprendizadoSpring.api.model.PedidoResumoModel;
import com.algaworks.AprendizadoSpring.api.model.input.PedidoInput;
import com.algaworks.AprendizadoSpring.api.openapi.controller.FluxoPedidoControllerOpenApi;
import com.algaworks.AprendizadoSpring.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.NegocioException;
import com.algaworks.AprendizadoSpring.domain.model.Pedido;
import com.algaworks.AprendizadoSpring.domain.model.Usuario;
import com.algaworks.AprendizadoSpring.domain.repository.PedidoRepository;
import com.algaworks.AprendizadoSpring.domain.service.EmissaoPedidoService;
import com.algaworks.AprendizadoSpring.domain.service.FluxoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/pedidos/{codigoPedido}")
public class FluxoPedidoController implements FluxoPedidoControllerOpenApi {

    @Autowired
    private FluxoPedidoService fluxoPedido;

    @Override
    @PutMapping(value = "/confirmacao", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> confirmar(@PathVariable String codigoPedido) {
        fluxoPedido.confirmar(codigoPedido);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping(value = "/cancelamento", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> cancelar(@PathVariable String codigoPedido) {
        fluxoPedido.cancelar(codigoPedido);

        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping(value = "/entrega", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> entregar(@PathVariable String codigoPedido) {
        fluxoPedido.entrega(codigoPedido);

        return ResponseEntity.noContent().build();
    }

}