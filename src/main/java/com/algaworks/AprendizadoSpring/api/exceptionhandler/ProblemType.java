package com.algaworks.AprendizadoSpring.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    PARAMENTRO_INVALDO("/parametro-invalido", "Parâmetro inválido"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADA("/recurso-nao-encontrada", "Recurso não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    NEGOCIO("/erro-negocio", "Violação de regra de negócio");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://localhost:8080" + path;
        this.title = title;
    }
}
