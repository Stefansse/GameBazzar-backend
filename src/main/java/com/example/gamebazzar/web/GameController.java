package com.example.gamebazzar.web;


import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
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
}