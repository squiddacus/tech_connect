import React from 'react';
import Button from 'react-bootstrap/Button';
import { LinkContainer } from 'react-router-bootstrap';
import './MealPlanCard.css';

const MealPlanCard = ({ mealPlan }) => {

//   mealPlan = {
//     meal_plan_id: 0,
//     meal_plan_title: 'unknown',
//     meal_plan_description: 'unknown',
    
// } //handleRecipeSelect //must create handleRecipeSelect function in App.js
  return (
    <div className="meal-plan-card">
      {/* <div className="meal-plan-id">ID: {mealPlan.id}</div> */}
      <div className="meal-plan-id">{mealPlan.title}</div>
      <div className="meal-plan-title">Description: {mealPlan.description}</div>

      <LinkContainer to={`/MealPlanPage/${mealPlan.id}`}>
                <Button className="details-button" variant="primary"> View Details </Button>
      </LinkContainer>
    </div>
  );
};
export default MealPlanCard;