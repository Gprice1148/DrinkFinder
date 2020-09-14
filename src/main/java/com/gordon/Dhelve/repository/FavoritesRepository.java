package com.gordon.Dhelve.repository;

import com.gordon.Dhelve.model.CocktailDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoritesRepository extends JpaRepository<CocktailDetail, Long> {
}