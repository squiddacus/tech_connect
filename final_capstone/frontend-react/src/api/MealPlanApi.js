

import { utilFetchWrapper } from "../services/utilFetchWrapper";
const fetchWrapper = utilFetchWrapper();


// const userContext = useContext(UserContext);
//   const currentUser = userContext.currentUser;
export function getMyMealPlans(userId) {
    const url = `/ViewMyMealPlans/${userId}`
    return fetchWrapper.get(url);
}

export function getMealPlanById(mealPlanId) {
    const url = `/MealPlan/${mealPlanId}`
    return fetchWrapper.get(url);
}

// export function getMealPlanById(mealPlanId) {
//     const url = `/RecipePage/${mealPlanId}`
//     return fetchWrapper.get(url)
// }

