// import React, {useEffect, useState} from 'react';
// import { Row } from 'react-bootstrap';
// import Layout from "../../components/ui/Layout";
// import RecipeCard from './RecipeCard';
// import { useContext } from 'react';
// import { UserContext } from '../../context/UserContext';
// import './MyRecipes.css'
// import { getMyMealPlans } from '../../api/MealPlanApi';

// const ViewMyMealPlans = ({children}) => {
//     const userContext = useContext(UserContext);
//     const currentUser = userContext.currentUser;
//     const[mealPlans, setMealPlans] = useState([]);
//     useEffect(() => {
//         const getMealPlansList = async () => {
//             const data = await getMyMealPlans(currentUser.id);
//             setMealPlans(data);
//         }
//         getMealPlansList()
//     }, [currentUser]);


//     useEffect(() => {
//         console.log(mealPlans)
//     }, [mealPlans])


//     return (
//         <Layout>
//             <div className="MyRecipeCards" style={{ flex: 1, justifyContent: "center" }}>
//                 <Row>
//                     {mealPlans.map(mealPlan => (
//                         <RecipeCard key={mealPlan.id} mealPlan={mealPlan} />
//                     ))}
//                 </Row>
//             </div>
//             {children}
//         </Layout>
//     );


// }

// export default ViewMyMealPlans ;


import React, { useEffect, useState, useContext } from 'react';
// import { Row } from 'react-bootstrap';
import Layout from '../../components/ui/Layout';
import MealPlanCard from './MealPlanCard'; // Import the new MealPlanCards component
import { UserContext } from '../../context/UserContext';
import { getMyMealPlans } from '../../api/MealPlanApi';
// import MealPlanCard from './MealPlanCard';
import './MyMealPlans.css'
const MyMealPlans = ({ children }) => {
  const userContext = useContext(UserContext);
  const currentUser = userContext.currentUser;
  const [mealPlans, setMealPlans] = useState([]);
  useEffect(() => {
    const getMealPlansList = async () => {
      const data = await getMyMealPlans(currentUser.id);

      setMealPlans(data);
    };
    if (currentUser)getMealPlansList();
  }, [currentUser]);
  return (
    <Layout>
      <div className="meal-plan-card">
        <h1>My Meal Plans</h1>
        <div>
         {mealPlans.map(mealPlan => 
            <MealPlanCard key={mealPlan.id} mealPlan={mealPlan} />
              )}
        </div>
      </div>
      {children}
    </Layout>
  );
};
export default MyMealPlans;