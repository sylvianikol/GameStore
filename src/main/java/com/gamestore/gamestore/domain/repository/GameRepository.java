package com.gamestore.gamestore.domain.repository;

import com.gamestore.gamestore.domain.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Game findById(long id);

    Optional<Game> findByTitle(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.price = :price WHERE g.id = :id")
    void updatePrice(@Param(value = "id") long id,
                     @Param(value = "price") BigDecimal price);

    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.size = :size WHERE g.id = :id")
    void updateSize(@Param(value = "id") long id,
                    @Param(value = "size") double size);

    @Modifying
    void deleteGameById(Long id);

    List<Game> findAll();

}
