package br.com.matheus.bertholucci.games.price.tracking.repository;

import br.com.matheus.bertholucci.games.price.tracking.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, String> {


}
