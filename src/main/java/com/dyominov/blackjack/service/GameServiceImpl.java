package com.dyominov.blackjack.service;

import com.dyominov.blackjack.model.Game;
import com.dyominov.blackjack.model.Player;
import com.dyominov.blackjack.model.Result;
import com.dyominov.blackjack.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class GameServiceImpl implements GameService {
    private GameRepository gameRepository;


    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    @Override
    public Result getResult(final Double score, final Double homeScore, final Double awayScore, final Double handicape) {
        List<Game> games = getAll();
        double size = games.size();
        double handicap = games.stream().filter(g -> g.getPlayer().getScore() + handicape > g.getDealer().getScore()).count() / size;
        double average = games.stream().filter(g -> g.getPlayer().getScore() + g.getDealer().getScore() > score).count() / size;
        double percentHome = games.stream().filter(g -> g.getPlayer().getScore() >= homeScore).count() / size;
        double percentAway = games.stream().filter(g -> g.getDealer().getScore() >= awayScore).count() / size;
        return new Result(percentHome, percentAway, average, handicap);
    }

    @Override
    public Game getGameById(String id) {
        return gameRepository.findById(id).get();
    }


    @Override
    public Game create(final Game game) {
        return gameRepository.save(game);
    }

    @Override
    public void deleteById(String id) {
        gameRepository.deleteById(id);
    }

    @Override
    public void parseData() {
        try (Scanner in = new Scanner(new File("input.txt"))) {
            List<Game> games = new ArrayList<>();
            for (int i = 0; i < 720; i++) {
                String date = in.nextLine();
                String[] teams = in.nextLine().trim().split(" - ");
                String[] score = in.nextLine().trim().split(" - ");
                String gameId = in.nextLine().trim();
                Player player;
                Player dealer;
                try {
                    player = new Player(teams[0], Integer.parseInt(score[0].substring(0, 2)));
                } catch (NumberFormatException e) {
                    player = new Player(teams[0], Integer.parseInt(score[0].substring(0, 1)));
                }
                try {
                    dealer = new Player(teams[1], Integer.parseInt(score[1].substring(0, 2)));
                } catch (NumberFormatException e) {
                    dealer = new Player(teams[1], Integer.parseInt(score[1].substring(0, 1)));
                }
                Game game = new Game(date,
                        player, dealer, Integer.parseInt(gameId));
                games.add(game);
            }
            gameRepository.saveAll(games);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
