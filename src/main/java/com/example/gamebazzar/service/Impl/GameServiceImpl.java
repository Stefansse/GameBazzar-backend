package com.example.gamebazzar.service.Impl;

import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.enumerations.Genre;
import com.example.gamebazzar.repository.GameRepository;
import com.example.gamebazzar.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game addGame(Game game) {
        return gameRepository.save(game);
    }

    @Override
    public Game editGame(Long gameId, Game game) {
        Game existingGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        existingGame.setTitle(game.getTitle());
        existingGame.setDescription(game.getDescription());
        existingGame.setReleaseDate(game.getReleaseDate());
        existingGame.setPrice(game.getPrice());
        existingGame.setGenre(game.getGenre());
        existingGame.setPublisher(game.getPublisher());
        existingGame.setRating(game.getRating());

        return gameRepository.save(existingGame);
    }

    @Override
    public Optional<Game> findGameById(Long gameId) {
        return gameRepository.findById(gameId);
    }

    @Override
    public Optional<Game> findGameByTitle(String title) {
        return Optional.ofNullable(gameRepository.findByTitle(title));
    }

    @Override
    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public void deleteGameById(Long gameId) {
        gameRepository.deleteById(gameId);
    }

    @Override
    public Game findGameByGenre(Genre genre) {
        return gameRepository.findByGenre(genre);
    }
}
