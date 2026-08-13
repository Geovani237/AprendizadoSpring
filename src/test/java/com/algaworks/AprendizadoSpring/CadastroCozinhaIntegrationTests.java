package com.algaworks.AprendizadoSpring;

import com.algaworks.AprendizadoSpring.domain.model.Cozinha;
import com.algaworks.AprendizadoSpring.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;


@SpringBootTest
class CadastroCozinhaIntegrationTests {

	@Autowired
	private CadastroCozinhaService cadastroCozinha;

	@Test
	public void testarCadastroCozinhaComSucesso() {
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
	public void testarCadastroCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome(null);

		ConstraintViolationException erroEsperado =
			Assertions.assertThrows(ConstraintViolationException.class, () -> {
				cadastroCozinha.salvar(novaCozinha);
			});

		Assertions.assertNotNull(erroEsperado);

	}

}
