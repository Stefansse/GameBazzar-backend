package com.example.gamebazzar.web;


import com.example.gamebazzar.model.DTO.filtersDTO.GameDeveloperFilterDTO;
import com.example.gamebazzar.model.DTO.filtersDTO.GameFilterDTO;
import com.example.gamebazzar.model.DTO.filtersDTO.GenreFilterDTO;
import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.service.GameService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "http://localhost:5173")

public class GameController {

    private final GameService gameService;



    public GameController(GameService gameService) {
        this.gameService = gameService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Game>> findAllGames() {
        List<Game> games = gameService.findAllGames();
        return ResponseEntity.ok(games);
    }

    // Find Game by ID
    @GetMapping("/{id}")
    public ResponseEntity<Game> findGameById(@PathVariable Long id) {
        Optional<Game> game = gameService.findGameById(id);
        return game.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Game> addGame(@RequestBody Game game) {
        Game savedGame = gameService.addGame(game);
        return ResponseEntity.ok(savedGame);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Game> editGame(@PathVariable Long id, @RequestBody Game game) {
        Game updatedGame = gameService.editGame(id, game);
        return ResponseEntity.ok(updatedGame);
    }

    // Find Game by Title
    @GetMapping("/title/{title}")
    public ResponseEntity<Game> findGameByTitle(@PathVariable String title) {
        Optional<Game> game = gameService.findGameByTitle(title);
        return game.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/filterByDeveloper")
    public ResponseEntity<List<Game>> filterGamesByPublisher(@RequestBody GameDeveloperFilterDTO filterDTO) {
        List<Game> filteredGames = gameService.filterGamesByPublisher(filterDTO.getPublisher());
        return ResponseEntity.ok(filteredGames);
    }

    @PostMapping("/filterByGenre")
    public ResponseEntity<List<Game>> filterByGenre(@RequestBody GenreFilterDTO genreFilterDTO) {
        List<Game> filteredGames = gameService.filterGamesByGenre(genreFilterDTO.getGenre());
        return ResponseEntity.ok(filteredGames);
    }

    @GetMapping("/api/games/sorted")
    public List<Game> getGamesSortedByPrice(@RequestParam(defaultValue = "asc") String sortOrder) {
        return gameService.findAllGamesSortedByPrice(sortOrder);
    }

    @PostMapping("/filter/publisher")
    public List<Game> filterByPublisher(@RequestBody GameDeveloperFilterDTO filterDTO) {
        return gameService.filterGamesByPublisher(filterDTO.getPublisher());
    }

    @PostMapping("/filter")
    public ResponseEntity<List<Game>> filterGames(@RequestBody GameFilterDTO filterDTO) {
        List<Game> filteredGames = gameService.filterGames(
                filterDTO.getGenre(),
                filterDTO.getPublisher()
        );
        return ResponseEntity.ok(filteredGames);
    }


    @GetMapping("/search")
    public List<Game> searchGamesByTitle(@RequestParam String title) {
        return gameService.getGamesByTitle(title);
    }


    @DeleteMapping("/{gameId}")
    public ResponseEntity<String> deleteGameById(@PathVariable Long gameId) {
        try {
            gameService.deleteGameById(gameId);
            return ResponseEntity.ok("Game with ID " + gameId + " has been deleted successfully.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the game.");
        }
    }

}
