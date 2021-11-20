package br.com.matheus.bertholucci.games.price.tracking.controller;

import br.com.matheus.bertholucci.games.price.tracking.model.Game;
import br.com.matheus.bertholucci.games.price.tracking.repository.GameRepository;
import br.com.matheus.bertholucci.games.price.tracking.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v2")
@Slf4j
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public List<Game> findAll(){
        return gameService.findAll();
    }

    @PostMapping("/game")
    public Game save(@RequestBody Game dto){
        return gameService.save(dto);
    }
}
