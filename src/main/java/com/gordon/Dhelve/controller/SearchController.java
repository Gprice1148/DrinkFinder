package com.gordon.Dhelve.controller;

import com.gordon.Dhelve.model.CocktailDetail;
import com.gordon.Dhelve.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @Autowired
    CocktailService cocktailService;

    @GetMapping("/search")
    public String searchForCocktailDetail(Model model,
        @RequestParam("searchQuery") String searchQuery) {
        CocktailDetail cocktailDetail = cocktailService.getCocktailDetail(searchQuery);

        if (cocktailDetail != null) {
            model.addAttribute("cocktailDetail", cocktailDetail);
            model.addAttribute("template", "searchresults");
        } else {
            model.addAttribute("noSearchResultFlag", true);
            model.addAttribute("template", "indexsearch");
        }

        return "index";
    }

}
