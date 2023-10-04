// this page is for users to create a meal plan with title and description inputs


import React, { useState } from 'react';
import { Form, Button } from 'react-bootstrap';
//import Header from '../../../Header.js'; // Import your Header component
import Header from '../../components/ui/Header';
const CreateMealPlan = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  // ... other state and handlers ...
  const handleSaveMealPlan = () => {
    // Logic to save the meal plan
  };
  return (
    <div>
      <Header /> {/* Render the header only once here */}
      <Form>
        <Form.Group controlId="title">
          <Form.Label>Title</Form.Label>
          <Form.Control
            type="text"
            placeholder="Enter title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </Form.Group>
        <Form.Group controlId="description">
          <Form.Label>Description</Form.Label>
          <Form.Control
            as="textarea"
            rows={4}
            placeholder="Enter description"
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
        </Form.Group>
        {/* ... other form inputs ... */}
        <Button className="add-button" onClick={handleSaveMealPlan}>
          Save Meal Plan
        </Button>
      </Form>
    </div>
  );
};
export default CreateMealPlan;


