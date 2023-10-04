// import { UserContext } from "../context/UserContext";
// import { useContext } from 'react';
import { utilFetchWrapper } from "../services/utilFetchWrapper";
const fetchWrapper = utilFetchWrapper();


// const userContext = useContext(UserContext);
//   const currentUser = userContext.currentUser;
export function getMyRecipes(userId) {
    const url = `/MyRecipes/${userId}`
    return fetchWrapper.get(url);
}

export function getRecipeById(recipeId) {
    const url = `/RecipePage/${recipeId}`
    return fetchWrapper.get(url)
}