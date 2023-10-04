import { createSection } from "../../api/SectionApi";
import { useState } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';
import './SectionCard.css'

function CreateSectionModal({newSection, setNewSection, mealPlanId}) {
  const [show, setShow] = useState(false);
  const [newSectionTitle, setNewSectionTitle] = useState();

  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const handleCreate = async (event) => {
    event.preventDefault();
    console.log('New section created.')
    console.log(newSectionTitle)
    const jSONObject = `{"sectionTitle":"${newSectionTitle}", "mealPlanId": ${mealPlanId}}`
    console.log(jSONObject)
    await createSection(JSON.parse(jSONObject));
    setNewSection((current) => !current);
    setNewSectionTitle(newSectionTitle);
    handleClose();
  } 
  const doSetTitle = (event) => {
      setNewSectionTitle(event.target.value);
  }

  return (
    <>
      <Button variant="primary" onClick={handleShow} className="add-button">
        Create New Meal
      </Button>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Create New Meal</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            {/* <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
              <Form.Label>New Meal Title</Form.Label>
              <Form.Control
                // type="email"
                placeholder="New Yummy Meal"
                autoFocus
              />
            </Form.Group> */}
            <Form.Group
              className="mb-3"
              controlId="exampleForm.ControlTextarea1"
            >
              <Form.Label>New Section Title</Form.Label>
              <Form.Control as="textarea" rows={1} value={newSectionTitle} onChange={doSetTitle} />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Close
          </Button>
          <Button variant="primary" onClick={handleCreate}className="add-button">
            Save Changes
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  );
}

export default CreateSectionModal;