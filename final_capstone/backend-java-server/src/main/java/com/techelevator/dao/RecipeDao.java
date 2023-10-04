package com.techelevator.dao;

import com.techelevator.model.Recipe;
import com.techelevator.model.User;

import java.util.List;

public interface RecipeDao {

    List<Recipe> findAll();

    Recipe getRecipeById(int recipeId);

    Recipe findByName(String recipeName);

//    int findIdByUsername(String username);

    void create(String recipeName, String recipeImg, String description, String ingredients, int time, int servings);

    List<Recipe> getRecipeByCustomerId(int customerId);


    List<Recipe> getRecipeBySectionId(int sectionId);

    List<Recipe> getRemainingRecipesBySectionId(int sectionId);
}
