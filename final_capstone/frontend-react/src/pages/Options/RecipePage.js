import {useState, useEffect} from "react";
import Layout from "../../components/ui/Layout";
import { getRecipeById } from "../../api/RecipeApi";
import { useParams } from "react-router-dom";
//import { withRouter } from "react-router-dom";
import './RecipePageStyle.js'
import {
  RecipeContainer,
  RecipeIngredients,
  RecipeInstructions,
  RecipeOverviewContainer,
  RecipePageHeader,
} from './RecipePageStyle.js';


 const RecipePage = ({children}) => {

    const [recipe, setRecipe] = useState([]);

    const {recipeId} = useParams();

    useEffect( () => {
        const getRecipe  = async() => {
            const data = await getRecipeById(recipeId);
            setRecipe(data)
        }
        getRecipe();
    }, [recipeId])

    console.log(typeof recipeId)
    console.log(recipeId)


    return (
      <Layout>
        <RecipeContainer>
          <RecipePageHeader title={recipe.recipeName} />
          <div>
              <p>
                <em>{recipe.description}</em>  
              </p>
            </div> 
          <RecipeOverviewContainer>
            <img src={recipe.recipeImg} alt={recipe.recipeName} />  
            <div>
              <p>
                <strong>Preparation Time:</strong> {recipe.time} mins
              </p>
              <p>
                <strong>Servings:</strong> {recipe.servings}
              </p>
              <p>
                <strong>Categories:</strong> {recipe.categories}
              </p>
            </div>
          </RecipeOverviewContainer>
          <h3>Ingredients</h3>
          <RecipeIngredients>
            <ul>
            {String(recipe.ingredients).split(',').map((item) => {
              return (
                <li>
                  {item}
                </li>
              );
              })}
            </ul>
          </RecipeIngredients>

          <RecipeInstructions>
          {recipe.instructions && (
            <>
              <h3>Instructions</h3>
              {/* <h4 dangerouslySetInnerHTML={{ __html: recipe.instructions }} /> */}
              <ol type="1">
                {String(recipe.instructions).split('*').map((item) => {
                  return (
                    <li> {item} </li>
                );
                })}
              </ol>
            </>
          )}
          </RecipeInstructions>
          
          </RecipeContainer>
      </Layout>
    );
  }

  export default RecipePage;