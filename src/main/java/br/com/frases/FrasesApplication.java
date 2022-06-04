package br.com.frases;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.frases.models"})
@EnableJpaRepositories("br.com.frases.repositories")
@ComponentScan("br.com.frases.resources")
@ComponentScan("br.com.frases.config")
@ComponentScan("br.com.frases.services")
public class FrasesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrasesApplication.class, args);
	}

}
