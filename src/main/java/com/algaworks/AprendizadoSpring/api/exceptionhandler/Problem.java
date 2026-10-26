package com.algaworks.AprendizadoSpring.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@Schema(name = "Problema")
public class Problem {

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "http://algafood.com.br/dados-invalidos")
    private String type;

    @Schema(example = "Dados inválidos")
    private String title;

    @Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private String detail;

    @Schema(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente")
    private String userMessage;

    @Schema(example = "2026-10-26T11:21:50.902245498Z")
    private OffsetDateTime timestamp;

    @Schema(example = "Lista de objetos ou campos que geraram o erro")
    private List<Object> objects;

    @Getter
    @Builder
    @Schema(example = "ObjectoProblema")
    public static class Object {

        @Schema(example = "preco")
        private String name;

        @Schema(example = "O preco é inválido")
        private String userMessager;
    }
}
