package com.techelevator.dao;

import com.techelevator.model.Recipe;
import com.techelevator.model.Section;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SectionDao {

    List<Section> findAll();
    List<Section> findBySectionTitle(String sectionTitle);
    Section getSectionBySectionId(int sectionId);

    List<Section> getSectionsByMealPlanId(int mealPlanId);

    boolean addRecipeToSection(int sectionId, int recipeId);

    Section create(String sectionTitle);

    void addSectionToMealPlan(int sectionId, int mealPlanId);

}
