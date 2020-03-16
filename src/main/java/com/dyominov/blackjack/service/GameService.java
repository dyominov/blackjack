package com.dyominov.blackjack.service;

import com.dyominov.blackjack.model.Game;
import com.dyominov.blackjack.model.Result;

import java.util.List;

public interface GameService {

    List<Game> getAll();

    Game getGameById(String id);

    Game create(Game game);

    Result getResult(final Double score, final Double homeScore, final Double awayScore, final Double handicape);

    void deleteById(String id);

    void parseData();
}


