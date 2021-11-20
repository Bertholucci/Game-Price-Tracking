package br.com.matheus.bertholucci.games.price.tracking;

import br.com.matheus.bertholucci.games.price.tracking.controller.GameController;
import br.com.matheus.bertholucci.games.price.tracking.model.Game;
import br.com.matheus.bertholucci.games.price.tracking.service.GameService;
import br.com.matheus.bertholucci.games.price.tracking.webscrap.NuuvemScrap;
import br.com.matheus.bertholucci.games.price.tracking.webscrap.SteamScrap;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.InjectService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = {"br.com.matheus.bertholucci.games.price.tracking.model"})
@EnableJpaRepositories(basePackages = {"br.com.matheus.bertholucci.games.price.tracking.repository"})
@Slf4j
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
}
