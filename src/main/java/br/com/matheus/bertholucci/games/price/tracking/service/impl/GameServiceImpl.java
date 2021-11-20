package br.com.matheus.bertholucci.games.price.tracking.service.impl;


import br.com.matheus.bertholucci.games.price.tracking.exception.EmptyFieldException;
import br.com.matheus.bertholucci.games.price.tracking.model.Game;
import br.com.matheus.bertholucci.games.price.tracking.repository.GameRepository;
import br.com.matheus.bertholucci.games.price.tracking.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository repository;

    @Override
    public Game save(Game dto) {
        return repository.save(dto);
    }

    @Override
    public List<Game> findAll() {
        return repository.findAll();
    }

}
