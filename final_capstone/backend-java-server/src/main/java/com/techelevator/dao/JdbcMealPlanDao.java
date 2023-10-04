package com.techelevator.dao;

import com.techelevator.model.MealPlan;
import com.techelevator.model.Recipe;
import com.techelevator.model.Section;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/* TODO: CHECK EVERYTHING HERE
*   we think we checked everything here*/
@Component
public class JdbcMealPlanDao implements MealPlanDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcMealPlanDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<MealPlan> findAll() {
        List<MealPlan> mealPlans = new ArrayList<>();
        String sql = "select * from meal_plans";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            MealPlan mealPlan = mapRowToMealPlan(results);
            mealPlans.add(mealPlan);
        }

        return mealPlans;
    }

    @Override
    public MealPlan getMealPlanById(int mealPlanId) {
        String sql = "SELECT * FROM meal_plans WHERE meal_plan_id= ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, mealPlanId);
        if (results.next()) {
            return mapRowToMealPlan(results);
        } else {
            return null;
        }
    }

    @Override
    public MealPlan findByName(String mealPlanName) {
        if (mealPlanName == null) throw new IllegalArgumentException("MealPlan name cannot be null");

        for (MealPlan mealPlan : this.findAll()) {
            if (mealPlan.getTitle().equalsIgnoreCase(mealPlanName)) {
                return mealPlan;
            }
        }
        throw new UsernameNotFoundException("MealPlan " + mealPlanName + " was not found.");
    }

    @Override
    public MealPlan saveMealPlan(MealPlan mealPlan) {
        String sql = "INSERT INTO meal_plans (meal_plan_title, meal_plan_description) VALUES (?, ?)";
        try (PreparedStatement statement = (PreparedStatement) jdbcTemplate.queryForRowSet(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, mealPlan.getTitle());
            statement.setString(2, mealPlan.getDescription());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Saving meal plan failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    mealPlan.setId(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Saving meal plan failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exception
        }
        return mealPlan;
    }

//    @Override
//    public MealPlan saveMealPlan(MealPlan mealPlan) {
//        return null;
//    }

    @Override
    public void create(String meal_plan_title, String meal_plan_description) {
        String insertUserSql = "insert into meal_plans (meal_plan_title ,meal_plan_description) values (?,?)";
        jdbcTemplate.update(insertUserSql, meal_plan_title, meal_plan_description);
    }

    @Override
    public List<MealPlan> getMealPlanByUserId(int userId){
        List<MealPlan> meal_plans = new ArrayList<>();
        String sql = "select * from meal_plans where meal_plan_id IN (select meal_plan_id from meal_plan_users where user_id = ?) ORDER BY meal_plan_title ASC";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while (results.next()) {
            meal_plans.add(mapRowToMealPlan(results));
        }
        return meal_plans;
    }

    private MealPlan mapRowToMealPlan(SqlRowSet rs) {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setId((long) rs.getInt("meal_plan_id"));
        mealPlan.setTitle(rs.getString("meal_plan_title"));
        mealPlan.setDescription(rs.getString("meal_plan_description"));

        return mealPlan;
    }



}