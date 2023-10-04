import { useParams } from "react-router";
import React, {useEffect, useState} from 'react';
import { getSectionsByMealPlanId } from "../../api/SectionApi";
import Layout from "../../components/ui/Layout";
import SectionCard from "./SectionCard";
import { CardGroup, Container } from "react-bootstrap";
import { getMealPlanById } from "../../api/MealPlanApi";
import CreateSectionModal from "./CreateSection";
import './MealPlanPage.css'

const MealPlanPage = ({children}) => {
    const {mealPlanId} = useParams();
    const [sections, setSections] = useState([]);
    const [mealPlan, setMealPlan] = useState('');
    const [newSection, setNewSection] = useState();
    useEffect (() => {
        const getSections = async () => {
            const data = await getSectionsByMealPlanId(mealPlanId);
            setSections(data);
        }
        getSections();
        const getMealPlan = async() => {
            const data = await getMealPlanById(mealPlanId);
            setMealPlan(data);
        }
        getMealPlan();
    }, [newSection, mealPlanId]);

    console.log(sections);

    return (
        <Layout>
            <div style={{
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                }}>
                <h1 className="title">{mealPlan.title}</h1>
            </div>
            <div className="outer">
            <Container className="page">
            <CreateSectionModal newSection={newSection} setNewSection={setNewSection} mealPlanId={mealPlanId}/>
            <CardGroup className="overflow-auto">
                <div className="d-flex flex-nowrap">
                {sections.map(section => (
                    <SectionCard key={section.id} section={section} mealPlanId={mealPlanId}/>
                ))}
                </div>
                
            </CardGroup>
            </Container>
            </div>
            {children}
        </Layout>
    );
 }

export default MealPlanPage;