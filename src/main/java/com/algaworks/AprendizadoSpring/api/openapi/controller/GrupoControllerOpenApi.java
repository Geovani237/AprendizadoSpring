package com.algaworks.AprendizadoSpring.api.openapi.controller;

import com.algaworks.AprendizadoSpring.api.exceptionhandler.Problem;
import com.algaworks.AprendizadoSpring.api.model.GrupoModel;
import com.algaworks.AprendizadoSpring.api.model.input.GrupoInput;
import io.swagger.annotations.*;

import java.util.List;

@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {


    @ApiOperation("Lista grupos")
    public List<GrupoModel> listar();


    @ApiOperation("Busca grupo por Id")
    @ApiResponses({
            @ApiResponse(code = 400, message = "ID do grupo inválido", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrada", response = Problem.class)
    })
    public GrupoModel buscar(@ApiParam(value = "ID de um grupo", example = "1")
                                 Long grupoId);



    @ApiOperation("Cadastra grupo")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Grupo cadastrado"),
    })
    public GrupoModel salvar(@ApiParam(name = "corpo", value = "Representação de um grupo")
                                 GrupoInput grupoInput);



    @ApiOperation("Atualiza grupo por Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Grupo atualizado", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrada", response = Problem.class)
    })
    public GrupoModel atualizar(@ApiParam(value = "ID de um grupo", example = "1")
                                    Long grupoId,
                                @ApiParam(value = "corpo", example = "Representação de um grupo")
                                GrupoInput grupoInput);


    @ApiOperation("Remove grupo por Id")
    @ApiResponses({
            @ApiResponse(code = 204, message = "Grupo excluído", response = Problem.class),
            @ApiResponse(code = 404, message = "Grupo não encontrada", response = Problem.class)
    })
    public void remover(@ApiParam(value = "ID do grupo", example = "1")
                            Long grupoId);
}
