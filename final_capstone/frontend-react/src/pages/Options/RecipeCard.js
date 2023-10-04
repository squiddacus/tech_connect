import React from 'react';
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import { LinkContainer } from 'react-router-bootstrap';
import Nav from 'react-bootstrap/Nav';
import './RecipeCard.css'

// const RecipeCard = ({
//    recipe = {
//    id: 1,
//    name: 'unknown',
//    username: 'a123456'
//    }, handleSaveRecipe
// }) => {
//    const options = {
//    width: 500,
//    height: 300,
//    text: person.name,
//    fontSize: '20'
//    }
// }

const RecipeCard = ({
    recipe = {
        id: 0,
        recipeName: 'unknown',
        recipeImg: 'unknown',
        description: '',
        ingredients: '',
        time: null,
        servings: null,
        instructions: null,
        categories: null
    }, //handleRecipeSelect //must create handleRecipeSelect function in App.js
}) => {
    // const options = {
    //     width: 500,
    //     height: 300,
    //     text: recipe.name,
    //     fontSize: '20'
    // }

    return (
        <Col lg={4} key={recipe.id}>
        <Card style={{ width: '30rem'}} className="mx-auto mt-4">
         <Card.Img variant="top" src={recipe.recipeImg} className="Recipe-img"/>
         <Card.Body>
           <Card.Title>{recipe.recipeName}</Card.Title>
           <Card.Text className="Recipe-text"> {recipe.description} </Card.Text>
           <LinkContainer to={`/RecipePage/${recipe.id}`}>
                <Button className="details-button" variant="primary">View Details</Button>
            </LinkContainer>
         </Card.Body>
       </Card>
       </Col>
    );

    // return (
    //     <div>
    //         <img src={recipe.image} alt={recipe.name} />
    //         <div>
    //             <h4>{recipe.name}</h4>
    //         </div>
    //     </div>
    // );

}


   
export default RecipeCard;