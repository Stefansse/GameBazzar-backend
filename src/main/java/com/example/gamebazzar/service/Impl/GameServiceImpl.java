package com.example.gamebazzar.service.Impl;

import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.enumerations.Genre;
import com.example.gamebazzar.repository.GameRepository;
import com.example.gamebazzar.service.GameService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Game> filterGamesByPublisher(String publisher) {
        return gameRepository.findByPublisherIgnoreCase(publisher);
    }

    @Override
    public List<Game> filterGamesByGenre(Genre genre) {
        return gameRepository.findByGenre(genre);
    }

    public List<Game> filterByPublisher(String publisher) {
        if (publisher == null || publisher.isEmpty()) {
            return gameRepository.findAll();
        }
        return gameRepository.findByPublisherIgnoreCase(publisher);
    }

    @Override
    public List<Game> findAllGamesSortedByPrice(String sortOrder) {
        Sort sort = Sort.by("price"); // Sort by the "price" field
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sort = sort.descending(); // Sort in descending order (high to low)
        } else {
            sort = sort.ascending(); // Default to ascending order (low to high)
        }
        return gameRepository.findAll(sort);
    }

    // Update Service Layer
    public List<Game> filterGames(String genre, String publisher) {
        List<Game> filteredGames = gameRepository.findAll();

        if (genre != null && !genre.isEmpty()) {
            filteredGames = filteredGames.stream()
                    .filter(game -> game.getGenre().name().equalsIgnoreCase(genre))
                    .collect(Collectors.toList());
        }

        if (publisher != null && !publisher.isEmpty()) {
            filteredGames = filteredGames.stream()
                    .filter(game -> game.getPublisher().equalsIgnoreCase(publisher))
                    .collect(Collectors.toList());
        }

        return filteredGames;
    }

    @Override
    public List<Game> getGamesByTitle(String title) {
        return gameRepository.findByTitleContainingIgnoreCase(title);
    }


}
