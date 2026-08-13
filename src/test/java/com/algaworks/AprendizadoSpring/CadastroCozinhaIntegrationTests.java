package com.algaworks.AprendizadoSpring;

import com.algaworks.AprendizadoSpring.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.exception.EntidadeEmUsoException;
import com.algaworks.AprendizadoSpring.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.AprendizadoSpring.domain.model.Cozinha;
import com.algaworks.AprendizadoSpring.domain.service.CadastroCozinhaService;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;


@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;
	  	  // Escreva bons nomes para os testes:
		  //    shouldComportamentoEsperado_WhileEstadoEmTeste()
		  //    whenCadastroCozinhaComDadosCorretos_ThenDeveAtribuirId()
		  //    givenJaExisteCozinhaChinesa_WhenCadastroCozinhaChinesa_ThenDeveFalhar()
	@Test //    givenPrecondicoes_WhenEstadoEmTeste_ThenComportamentoEsperao
	public void deveAtribuirId_QuandoCadastrarCozinhaComDadosCorretos() {
		//cenário
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		//ação
		novaCozinha = cadastroCozinha.salvar(novaCozinha);

		//validação
		Assertions.assertNotNull(novaCozinha);
		Assertions.assertNotNull(novaCozinha.getId());
	}

	@Test
	public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);

		ConstraintViolationException erroEsperado =
			Assertions.assertThrows(ConstraintViolationException.class, () -> {
				cadastroCozinha.salvar(novaCozinha);
			});

		Assertions.assertNotNull(erroEsperado);

	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaEmUso() {

		EntidadeEmUsoException erroEsperado =
				Assertions.assertThrows(EntidadeEmUsoException.class, () -> {
					cadastroCozinha.excluir(1L);
				});
		Assertions.assertNotNull(erroEsperado);
	}

	@Test
	public void deveFalhar_QuandoExcluirCozinhaIndexistente() {

		CozinhaNaoEncontradaException erroEsperado =
				Assertions.assertThrows(CozinhaNaoEncontradaException.class, () -> {
					cadastroCozinha.excluir(30L);
				});
		Assertions.assertNotNull(erroEsperado);
	}

}
