package com.techelevator.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.techelevator.dao.MealPlanDao;
import com.techelevator.dao.RecipeDao;
import com.techelevator.dao.SectionDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(path="/api")
public class ApplicationController {
    /****************************************************************************
     * You application controller code should go here
     ******************************************************************************/
    private RecipeDao recipeDao;
    private UserDao userDao;

    private MealPlanDao mealPlanDao;

    private SectionDao sectionDao;
    // constructor
    public ApplicationController(RecipeDao recipeDao, UserDao userDao, MealPlanDao mealPlanDao, SectionDao sectionDao) {
        this.recipeDao = recipeDao;
        this.userDao = userDao;
        this.mealPlanDao = mealPlanDao;
        this.sectionDao = sectionDao;
    }

    // decide the URL path the front end will call to get recipes
    @GetMapping (value="/MyRecipes/{id}")
    public List<Recipe> getRecipesList(@PathVariable("id") final int id) {
        logAPICall("HTTP GET request received with URL /MyRecipes");
        return recipeDao.getRecipeByCustomerId(id);
    }

    @GetMapping (value="/ViewMyMealPlans/{id}")
    public List<MealPlan> getMealPlansList(@PathVariable("id") final int id) {
        logAPICall("HTTP GET request received with URL /ViewMyMealPlans");
        return mealPlanDao.getMealPlanByUserId(id);
    }

    @GetMapping (value="/MealPlan/{id}")
    public MealPlan getMealPlanById(@PathVariable("id") final int id) {
        logAPICall("HTTP GET request received with URL /ViewMyMealPlans");
        return mealPlanDao.getMealPlanById(id);
    }

    @GetMapping (value="/MealPlanPage/{id}")
    public Section getSectionBySectionId(@PathVariable("id") final int id) {
        logAPICall("HTTP GET request received with URL /MealPlanPage");
        return sectionDao.getSectionBySectionId(id);
    }

    @GetMapping (value="/MealPlanPage/recipe/{id}")
    public List<Recipe> getRecipesBySectionId(@PathVariable("id") final int id) {
        logAPICall("HTTP GET request received with URL /MealPlanPage/recipe");
        return recipeDao.getRecipeBySectionId(id);
    }

    @GetMapping (value="/MealPlanPage/remainingrecipe/{id}")
    public List<Recipe> getRemainingRecipesBySectionId(@PathVariable("id") final int id) {
        logAPICall("HTTP GET request received with URL /MealPlanPage/remainingrecipe");
        return recipeDao.getRemainingRecipesBySectionId(id);
    }

    @GetMapping (value="/MealPlanPage/section/{id}")
    public List<Section> getSectionsByMealPlanId(@PathVariable("id") final int id) {
        logAPICall("HTTP GET request received with URL /MealPlanPage/recipe");
        return sectionDao.getSectionsByMealPlanId(id);
    }

    @GetMapping (value="/RecipePage/{recipeId}")
    public Recipe getRecipeById(@PathVariable("recipeId") final int recipeId) {
        logAPICall("HTTP GET request received with URL /RecipePage");
        return recipeDao.getRecipeById(recipeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/addrecipetosection")
    public boolean addRecipeToSection(@RequestBody ObjectNode objectNode) {
        logAPICall("HTTP POST request received with URL /addrecipetosection");
        Integer sectionId = objectNode.get("sectionId").asInt();
        Integer recipeId = objectNode.get("recipeId").asInt();
        return sectionDao.addRecipeToSection(sectionId, recipeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/createsection")
    public void createSection(@Valid @RequestBody ObjectNode objectNode) {
        logAPICall("HTTP POST request received with URL /addrecipetosection");
        String sectionTitle = objectNode.get("sectionTitle").asText();
        int mealPlanId = objectNode.get("mealPlanId").asInt();
        Section newSection = sectionDao.create(sectionTitle);
        sectionDao.addSectionToMealPlan(newSection.getId(), mealPlanId);
    }

    /**
     * Helper method to log API calls made to the server
     *
     * @param message - message to be included in the server log
     */
    public void logAPICall(String message) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.A");
        String timeNow = now.format(formatter);
        System.out.println(timeNow + "-" + message);

    }
}
