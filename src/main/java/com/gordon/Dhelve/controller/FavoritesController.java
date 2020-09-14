package com.gordon.Dhelve.controller;

import com.gordon.Dhelve.model.CocktailDetail;
import com.gordon.Dhelve.service.FavoritesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@AllArgsConstructor
public class FavoritesController {

    private final FavoritesService favoritesService;

    @PostMapping("/addToFavorites")
    public String addCocktailDetailToFavorites(
        @ModelAttribute(value = "cocktailDetail") CocktailDetail cocktailDetail,
        RedirectAttributes redirectAttributes) {
        favoritesService.save(cocktailDetail);
        return "redirect:/";
    }
}
