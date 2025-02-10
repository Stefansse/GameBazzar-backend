package com.example.gamebazzar.service;

import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.enumerations.Genre;

import java.util.List;
import java.util.Optional;

public interface GameService {
    // Create or Update a Game
    Game addGame(Game game);

    Game editGame(Long gameId, Game game);

    // Find a Game by ID
    Optional<Game> findGameById(Long gameId);

    // Find a Game by Title
    Optional<Game> findGameByTitle(String title);

    // Get all Games
    List<Game> findAllGames();

    // Delete a Game by ID
    void deleteGameById(Long gameId);




    public List<Game> filterGamesByPublisher(String publisher);

    List<Game> filterGamesByGenre(Genre genre);


    List<Game> findAllGamesSortedByPrice(String sortOrder);

    List<Game> filterGames(String genre, String publisher);

     List<Game> getGamesByTitle(String title);

    List<Game> filterGamesByRating(Double minRating);

}
