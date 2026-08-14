package com.algaworks.AprendizadoSpring;

import com.algaworks.AprendizadoSpring.domain.model.Cozinha;
import com.algaworks.AprendizadoSpring.domain.repository.CozinhaRepository;
import com.algaworks.AprendizadoSpring.util.DatabaseCleaner;
import com.algaworks.AprendizadoSpring.util.ResourceUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class CadastroCozinhaIT {

	private Cozinha cozinhaAmericana;
	private int quantidadeCozinhasCadastradas;
	private String jsonCozinhaChinesa = "/json/cozinha-chinesa.json";
	private static final Integer COZINHA_ID_INEXISTENTE = 100;

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@BeforeEach
	public void setUp() {
		//Ajuda a debugar
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cozinhas";
		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveRetornarStatus200_QuandoCunsultarCozinhas() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		RestAssured.given()
				.accept(ContentType.JSON)
			.when()
				.get()
			.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	public void deveHaverCozinhas_QuandoCunsultarCozinhas() {
		RestAssured.given()
					.accept(ContentType.JSON)
				.when()
					.get()
				.then()
					.body("", Matchers.hasSize(quantidadeCozinhasCadastradas));
	}

	@Test
	public void deveRetornarStatus201_QuandoCadastrarCozinha() {
		RestAssured.given()
				.body(ResourceUtil.getContentFromResource(jsonCozinhaChinesa))
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.when()
					.post()
				.then()
					.statusCode(HttpStatus.CREATED.value());
	}

	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoCulsultarCozinhaExistente() {
		RestAssured
				.given()
					.pathParam("cozinhaId", cozinhaAmericana.getId())
					.accept(ContentType.JSON)
				.when()
					.get("/{cozinhaId}")
				.then()
				.statusCode(HttpStatus.OK.value())
				.body("nome", Matchers.equalTo(cozinhaAmericana.getNome()));
	}

	@Test
	public void deveRetornarRespostaEStatus404_QuandoCulsultarCozinhaInexistente() {
		RestAssured
				.given()
					.pathParam("cozinhaId", COZINHA_ID_INEXISTENTE)
					.accept(ContentType.JSON)
				.when()
					.get("/{cozinhaId}")
				.then()
					.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDados() {
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Tailandesa");
		cozinhaRepository.save(cozinha1);

		this.cozinhaAmericana = new Cozinha();
		cozinhaAmericana.setNome("Americana");
		cozinhaRepository.save(cozinhaAmericana);

		quantidadeCozinhasCadastradas = (int) cozinhaRepository.count();

	}

	public static String getContentFromResource(String resourceName) {
		try {
			InputStream stream = ResourceUtils.class.getResourceAsStream(resourceName);
			return StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

// Um teste não pode depender da execução de outro teste e nem na não execução de um outro teste