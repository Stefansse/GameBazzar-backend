package com.example.gamebazzar.repository;

import com.example.gamebazzar.model.Game;
import com.example.gamebazzar.model.enumerations.Genre;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    Game findByTitle(String title);

    List<Game> findByGenre(Genre genre);

    List<Game> findByPublisher(String publisher);

    List<Game> findByPublisherIgnoreCase(String publisher);

    List<Game> findByTitleContainingIgnoreCase(String title);


    List<Game> findAll(Sort sort);


}
