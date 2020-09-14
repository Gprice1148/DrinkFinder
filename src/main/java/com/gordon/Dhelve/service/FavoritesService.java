package com.gordon.Dhelve.service;

import com.gordon.Dhelve.model.CocktailDetail;
import com.gordon.Dhelve.repository.FavoritesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FavoritesService {

    private final FavoritesRepository favoritesRepository;

    public void save(CocktailDetail cocktailDetail) {
        favoritesRepository.save(cocktailDetail);
    }
}
