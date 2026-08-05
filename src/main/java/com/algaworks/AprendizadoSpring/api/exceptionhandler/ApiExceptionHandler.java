package com.algaworks.AprendizadoSpring.api.exceptionhandler;

import com.algaworks.AprendizadoSpring.domain.exception.EntidadeEmUsoException;
import com.algaworks.AprendizadoSpring.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;


// @ControllerAdvice -> Componente interceptor (AOP) do Spring que centraliza
// o tratamento de exceções e a formatação de dados em nível de aplicação.
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    //ResponseEntityExceptionHandler -> Classe base estendível para manipular
    //e padronizar exceções nativas do ecossistema Spring MVC.

    //@ExceptionHandler -> Anotação que mapeia exceções específicas para métodos manipuladores
    //dentro de controllers ou conselheiros globais.
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradoException(EntidadeNaoEncontradaException ex, WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail).build();

//        Problem problem = Problem.builder()
//                .status(status.value())
//                .type("https://localhost:8080/entidade-nao-encontrada")
//                .title("Entidade não encontrada")
//                .detail(ex.getMessage())
//                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(),
                status, request);

    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(),HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(),HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        if (body == null) {
            body = Problem.builder()
                    .title(status.getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
                                                        ProblemType problemType, String detail) {
        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }
}


