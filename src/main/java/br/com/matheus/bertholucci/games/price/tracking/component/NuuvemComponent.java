package br.com.matheus.bertholucci.games.price.tracking.component;

import br.com.matheus.bertholucci.games.price.tracking.controller.GameController;
import br.com.matheus.bertholucci.games.price.tracking.model.Game;
import br.com.matheus.bertholucci.games.price.tracking.service.GameService;
import br.com.matheus.bertholucci.games.price.tracking.webscrap.NuuvemScrap;
import br.com.matheus.bertholucci.games.price.tracking.webscrap.SteamScrap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class NuuvemComponent implements CommandLineRunner {

    @Autowired
    private GameService gameService;

    @Override
    public void run(String...args) throws Exception {
        NuuvemScrap nuuvemScrap = new NuuvemScrap();

        try {
            /* process to save promotions from Nuuvem */
            List<Game> nuuvemList = nuuvemScrap.getDataFromNuuvem();

            for(Game dto : nuuvemList){
                gameService.save(dto);
            }

            log.info("\n\n\n\n INSERÇÃO FINALIZADA COM SUCESSO");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
