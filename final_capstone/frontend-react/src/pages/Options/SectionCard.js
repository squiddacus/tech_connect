import React, { useEffect, useState } from 'react';
import Card from 'react-bootstrap/Card';
import ListGroup from 'react-bootstrap/ListGroup';
import { getRecipeBySectionId } from '../../api/SectionApi';
import Col from 'react-bootstrap/Col';
import { LinkContainer } from 'react-router-bootstrap';
import Button from 'react-bootstrap/Button';
import DropdownRecipe from '../../components/ui/RecipeDropdown';
import { ButtonToolbar } from 'react-bootstrap';
import SelectRecipe from '../../components/ui/RecipeDropdown';
import './SectionCard.css';


const SectionCard = ({
    section = {
        id: null,
        title: null
    },
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
    },
    mealPlanId
}) => {
    const [recipes, setRecipe] = useState([]);
    const [isAdded, setIsAdded] = useState(false);
    console.log(section.id)
    useEffect (() => {
        const getRecipesForSection = async() => {
            const data = await getRecipeBySectionId(section.id);
            setRecipe(data)
        }
        getRecipesForSection()
    }, [section.id, isAdded])
    

    return (
        <div className="container-fluid py-2">
                <Col lg={4} key={section.id}>
                <Card style={{ width: '18rem' }}>
                    <Card.Body>
                        <Card.Title>{section.title}</Card.Title>
                    </Card.Body>
                    <ListGroup className="list-group-flush">
                        {recipes.map(recipe => (
                            <LinkContainer to={`/RecipePage/${recipe.id}`}>
                                <ListGroup.Item className="section-card">
                                    {recipe.recipeName}
                                    {/* <Button variant="outline-danger" className="ml-2">X</Button> */}
                                </ListGroup.Item>
                            </LinkContainer>
                        ))}
                    </ListGroup>
                    <Card.Body>
                        <SelectRecipe section={section} isAdded={isAdded} setIsAdded={setIsAdded} />
                        {/* <Button onClick={handleAddRecipe}>Add Another Recipe</Button> */}
                    </Card.Body>
                </Card>
                </Col>
        </div>
  );

}

export default SectionCard;