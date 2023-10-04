import { UserProvider } from './context/UserContext';
import ErrorBoundary from './ErrorBoundary';
import Login from './pages/Login/Login';
import Home from './pages/Home/Home';
import MyRecipes from './pages/Options/MyRecipes';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
//Import Option2 from './pages/Options/Option2';
import SignUp from './pages/SignUp/SignUp';
import MyMealPlans from './pages/Options/MyMealPlans';
import RecipePage from './pages/Options/RecipePage';
import ViewMyMealPlans from './pages/Options/ViewMyMealPlans';
import MealPlanPage from './pages/Options/MealPlanPage';


const MyApp = () => {  


  return (
    
    <div className="wrapper">
      <ErrorBoundary fallback={<p>Something went wrong</p>}>
        <UserProvider>
          <BrowserRouter>
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/Login" element={<Login />} />
              <Route path="/MyRecipes" element={<MyRecipes />} />
              <Route path="/MyMealPlans" element={<MyMealPlans />} />
              <Route path="/SignUp" element={<SignUp/>} />
              <Route path="/ViewMyMealPlans" element={<ViewMyMealPlans/>}/>
              <Route path="/RecipePage/:recipeId" element={<RecipePage/>} />
              <Route path="/MealPlanPage/:mealPlanId" element={<MealPlanPage />} />

          {/* <Route path="/products/:slug" element={<ProductDetails />} />
          <Route path="*" element={<NoMatch />} /> */}
            </Routes>
          </BrowserRouter>
        </UserProvider>
      </ErrorBoundary>
    </div>
  );
}

export default MyApp;