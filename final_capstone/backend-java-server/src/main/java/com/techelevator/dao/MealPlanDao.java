
package com.techelevator.dao;

import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import com.techelevator.model.Section;
import com.techelevator.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MealPlanDao {
    List<MealPlan> findAll();

    MealPlan getMealPlanById(int mealPlanId);

    MealPlan findByName(String mealPlanName);

    MealPlan saveMealPlan(MealPlan mealPlan);

//    void create(String recipeName, String recipeImg, String description, String ingredients, int time, int servings);

    void create(String meal_plan_title, String meal_plan_description);


    List<MealPlan> getMealPlanByUserId(int userId);


}

    /* TODO: Checkout getMealPlanByCustomerId */