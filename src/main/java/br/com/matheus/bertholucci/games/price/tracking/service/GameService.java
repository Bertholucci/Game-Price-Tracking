package br.com.matheus.bertholucci.games.price.tracking.service;

import br.com.matheus.bertholucci.games.price.tracking.model.Game;
import br.com.matheus.bertholucci.games.price.tracking.repository.GameRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService  {

    Game save(Game dto);

    List<Game> findAll();
}
