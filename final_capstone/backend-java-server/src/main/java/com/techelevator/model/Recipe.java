package com.techelevator.model;

public class Recipe {

    private int id;
    private String recipeName;
    private String recipeImg;
    private String description;
    private String ingredients;
    private int time;
    private int servings;
    private String instructions;
    private String categories;

    public Recipe() { }

    public Recipe(int id, String recipeName, String recipeImg, String description, String ingredients, int time, int servings, String instructions, String categories) {
        this.id = id;
        this.recipeName = recipeName;
        this.recipeImg = recipeImg;
        this.description = description;
        this.ingredients = ingredients;
        this.time = time;
        this.servings = servings;
        this.instructions = instructions;
        this.categories = categories;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeImg() {
        return recipeImg;
    }

    public void setRecipeImg(String recipeImg) {
        this.recipeImg = recipeImg;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}
