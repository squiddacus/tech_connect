package com.techelevator.dao;

import com.techelevator.model.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcRecipeDao implements RecipeDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRecipeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Recipe> findAll() {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "select * from recipes";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Recipe recipe = mapRowToRecipe(results);
            recipes.add(recipe);
        }

        return recipes;
    }

    @Override
    public Recipe getRecipeById(int recipeId) {
        String sql = "SELECT * FROM recipes WHERE recipe_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, recipeId);
        if (results.next()) {
            return mapRowToRecipe(results);
        } else {
            return null;
        }
    }

    @Override
    public Recipe findByName(String recipeName) {
        if (recipeName == null) throw new IllegalArgumentException("Recipe name cannot be null");

        for (Recipe recipe : this.findAll()) {
            if (recipe.getRecipeName().equalsIgnoreCase(recipeName)) {
                return recipe;
            }
        }
        throw new UsernameNotFoundException("Recipe " + recipeName + " was not found.");
    }

    @Override
    public void create(String recipeName, String recipeImg, String description, String ingredients, int time, int servings) {
        String insertUserSql = "insert into recipes (recipe_name,recipe_img,description, ingredients,time,servings) values (?,?,?,?,?,?)";
        jdbcTemplate.update(insertUserSql, recipeName, recipeImg, description, ingredients, time, servings);
    }

    @Override
    public List<Recipe> getRecipeByCustomerId(int userId){
        List<Recipe> recipes = new ArrayList<>();
        String sql = "select * from recipes where recipe_id IN (select recipe_id from recipes_users where user_id = ?) ORDER BY recipe_name ASC";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            recipes.add(mapRowToRecipe(results));
        }
        return recipes;
    }

    @Override
    public List<Recipe> getRecipeBySectionId(int sectionId) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "select * from recipes where recipe_id IN (select recipe_id_fk from sections_recipes where section_id_fk = ?)";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sectionId);
        while (results.next()) {
            recipes.add(mapRowToRecipe(results));
        }
        return recipes;
    }

    @Override
    public List<Recipe> getRemainingRecipesBySectionId(int sectionId) {
        List<Recipe> recipes = new ArrayList<>();
        String sql = "select * from recipes where recipe_id NOT IN (select recipe_id_fk from sections_recipes where section_id_fk = ?)";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sectionId);
        while (results.next()) {
            recipes.add(mapRowToRecipe(results));
        }
        return recipes;
    }

    private Recipe mapRowToRecipe(SqlRowSet rs) {
        Recipe recipe = new Recipe();
        recipe.setId(rs.getInt("recipe_id"));
        recipe.setRecipeName(rs.getString("recipe_name"));
        recipe.setRecipeImg(rs.getString("recipe_img"));
        recipe.setDescription(rs.getString("description"));
        recipe.setIngredients(rs.getString("ingredients"));
        recipe.setTime(rs.getInt("time"));
        recipe.setServings(rs.getInt("servings"));
        recipe.setInstructions(rs.getString("instructions"));
        recipe.setCategories(rs.getString("categories"));
        return recipe;
    }

}
