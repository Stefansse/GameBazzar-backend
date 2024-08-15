package com.example.gamebazzar.repository;

import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.enumerations.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByTitle(String title);

    Game findByGenre(Genre genre);
}
