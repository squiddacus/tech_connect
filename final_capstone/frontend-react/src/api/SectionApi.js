import { utilFetchWrapper } from "../services/utilFetchWrapper";
const fetchWrapper = utilFetchWrapper();

export function getRecipeBySectionId(id) {
    const url = `/MealPlanPage/recipe/${id}`
    return fetchWrapper.get(url);
}

export function getSectionsByMealPlanId(mealPlanId) {
    const url = `/MealPlanPage/section/${mealPlanId}`
    return fetchWrapper.get(url);
}

export function getRemainingRecipesBySectionId(id) {
    const url = `/MealPlanPage/remainingrecipe/${id}`
    return fetchWrapper.get(url);
}

export function addRecipeToSection(object) {
    const url = `/addrecipetosection`
    return fetchWrapper.post(url, object)
}

export function createSection(section) {
    const url = `/createsection`
    return fetchWrapper.post(url, section)
}