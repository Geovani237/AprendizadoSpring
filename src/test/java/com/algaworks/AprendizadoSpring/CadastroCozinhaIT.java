package com.algaworks.AprendizadoSpring;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CadastroCozinhaIT {

	@LocalServerPort
	private int pont;

	@Test
	public void deveRetornarStatus200_QuandoCunsultarCozinhas() {
		//Ajuda a debugar
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
				.basePath("/cozinhas")
				.port(pont)
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveConter4Cozinhas_QuandoCunsultarCozinhas() {
		//Ajuda a debugar
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
					.basePath("/cozinhas")
					.port(pont)
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.body("", Matchers.hasSize(4))
				.body("nome", Matchers.hasItems("Indiana", "Tailandesa"));

	}

}
