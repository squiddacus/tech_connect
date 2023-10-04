import Button from 'react-bootstrap/Button';
import React, { useEffect, useState } from "react";
import { getRemainingRecipesBySectionId } from '../../api/SectionApi';
import { addRecipeToSection } from '../../api/SectionApi';
import Form from 'react-bootstrap/Form';
import '../../../src/pages/Options/SectionCard.css'

function SelectRecipe({section, isAdded, setIsAdded}) {
    const [recipes, setRecipes] = useState([]);
    const [value, setValue] = useState('');
    
    console.log(recipes)
    useEffect(() => {
        const getRemainingRecipes = async() => {
            const data = await getRemainingRecipesBySectionId(section.id);
            setRecipes(data)
        }
        getRemainingRecipes()
    }, [isAdded, section.id])

    const handleAddRecipe = async (event) => {
      event.preventDefault();
      console.log("Added recipe")
      const addRecipeObject = `{"sectionId":${section.id}, "recipeId": ${value}}`
      const body = JSON.parse(addRecipeObject)
      await addRecipeToSection(body);
      setIsAdded((current) => !current);
      setValue('0');

  }

    const handleChange = async(event) => {
      setValue(event.target.value)
    }

  return (
    <div>
    <Form.Select aria-label="Default select example" onChange={handleChange}>
      <option value='0'>Select Recipe</option>
      {recipes.map(recipe => (
        <option value={recipe.id}>{recipe.recipeName}</option>
      ))}
    </Form.Select>

    <Button className="add-button" onClick={handleAddRecipe}>Add Recipe</Button>
    </div>
  );
}

export default SelectRecipe;