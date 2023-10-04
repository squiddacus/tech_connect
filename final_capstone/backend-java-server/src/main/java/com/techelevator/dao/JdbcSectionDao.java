package com.techelevator.dao;

import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import com.techelevator.model.Section;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSectionDao implements SectionDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSectionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Section> findAll() {
        List<Section> sections = new ArrayList<>();
        String sql = "select * from sections";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Section section = mapRowToSection(results);
            sections.add(section);
        }

        return sections;
    }

    @Override
    public List<Section> findBySectionTitle(String sectionTitle) {
        if (sectionTitle == null) throw new IllegalArgumentException("Section name cannot be null");

        for (Section section : this.findAll()) {
            if (section.getTitle().equalsIgnoreCase(sectionTitle)) {
                return (List<Section>) section;
            }
        }
        throw new UsernameNotFoundException("Section " + sectionTitle + " was not found.");
    }


    @Override
    public Section getSectionBySectionId(int sectionId){
        Section sections = null;
        String sql = "SELECT * FROM sections WHERE section_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, sectionId);
        if (results.next()) {
            return mapRowToSection(results);
        } else {
            return null;
        }
    }

    @Override
    public List<Section> getSectionsByMealPlanId(int mealPlanId) {
        List<Section> sections = new ArrayList<>();
        String sql = "select * from sections where section_id IN (select section_id_fk from meal_plan_sections where meal_plan_id_fk = ?)";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealPlanId);
        while (results.next()) {
            sections.add(mapRowToSection(results));
        }
        return sections;
    }

    @Override
    public boolean addRecipeToSection(int sectionId, int recipeId) {
        String sql = "INSERT INTO sections_recipes (section_id_fk, recipe_id_fk) VALUES (?,?)";
        return jdbcTemplate.update(sql, sectionId, recipeId) == 1;
    }

    @Override
    public Section create(String sectionTitle) {
        Section newSection = null;
        String strippedString = sectionTitle.replace("\"", "");
        String sql = "INSERT INTO sections (section_title) VALUES (?) RETURNING section_id;";
        int newSectionId = jdbcTemplate.queryForObject(sql, int.class,  strippedString);
        newSection = getSectionBySectionId(newSectionId);
        return newSection;
    }

    @Override
    public void addSectionToMealPlan(int sectionId, int mealPlanId) {
        String sql = "INSERT INTO meal_plan_sections (section_id_fk, meal_plan_id_fk) VALUES (?,?)";
        jdbcTemplate.update(sql, sectionId, mealPlanId);
    }

    private Section mapRowToSection(SqlRowSet rs) {
        Section section = new Section();
        section.setId((int) rs.getInt("section_id"));
        section.setTitle(rs.getString("section_title"));

        return section;
    }
}
