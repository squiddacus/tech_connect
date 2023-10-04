import React, {useEffect, useState} from 'react';
import { Row } from 'react-bootstrap';
import { getMyRecipes } from "../../api/RecipeApi";
import Layout from "../../components/ui/Layout";
import RecipeCard from './RecipeCard';
import { useContext } from 'react';
import { UserContext } from '../../context/UserContext';
import './MyRecipes.css'

const MyRecipes = ({children}) => {
    const userContext = useContext(UserContext);
    const currentUser = userContext.currentUser;
    const[recipes, setRecipes] = useState([]);
    useEffect(() => {
        const getRecipesList = async () => {
            const data = await getMyRecipes(currentUser.id);
            setRecipes(data);
        }
        getRecipesList()
    }, [currentUser]);


    useEffect(() => {
        console.log(recipes)
    }, [recipes])


    return (
        <Layout>
            <Row>
                {recipes.map(recipe => (
                <RecipeCard key={recipe.id} recipe={recipe} />
                ))}  
            </Row>
        {children}
      </Layout>
    );


}

export default MyRecipes;
