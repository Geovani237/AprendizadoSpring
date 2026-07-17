package com.algaworks.AprendizadoSpring;

import com.algaworks.AprendizadoSpring.infrastructure.repository.CustomJpaRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class)
public class AprendizadoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(AprendizadoSpringApplication.class, args);
	}

}
