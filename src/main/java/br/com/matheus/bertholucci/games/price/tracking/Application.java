package br.com.matheus.bertholucci.games.price.tracking;

import org.hibernate.service.spi.InjectService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.matheus.bertholucci.games.price.tracking.model"})
@EnableJpaRepositories(basePackages = {"br.com.matheus.bertholucci.games.price.tracking.repository"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
