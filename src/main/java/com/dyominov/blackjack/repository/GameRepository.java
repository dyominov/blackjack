package com.dyominov.blackjack.repository;

import com.dyominov.blackjack.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GameRepository extends MongoRepository<Game, String> {


    void deleteById(String id);
}
