import React, { useEffect } from 'react';
import './Home.css';
import Header from '../../components/ui/Header';
//import Container from 'react-bootstrap/container';
import Logo from '../../images/good-bytes-diagram.png';
//import backgroundImage from '../../images/vegan-meal-te-main-200527.jpg'
import FAQList from './FAQList';
const Home = ({ children }) => {
  useEffect(() => {
    const handleScroll = () => {
      const parallaxElements = document.querySelectorAll('.parallax-image');
      parallaxElements.forEach((element) => {
        const scrollPosition = window.scrollY;
        element.style.transform = `translateY(${scrollPosition * 0.5}px)`;
      });
    };
    window.addEventListener('scroll', handleScroll);
    return () => {
      window.removeEventListener('scroll', handleScroll);
    };
  }, []);
  return (
   
    <div className="parallax-container">
      <div className="parallax-image">
      <Header/>
        <div className="content">
        
          {/* <h1 className="big-bold-font">Good Bytes</h1> */}
          <p className="big-bold-font2">Elevate Your Meals with Good Bytes: Your Ultimate Meal Planning Companion!</p>
          {children} {/* This will render the dropdown menu */}
        </div>
        <p>

        </p>
        <p></p>
    
        <div className="text-container">
      
          <p style={{fontSize: '20px', font: 'Merriweather'}}> Welcome to Good Bytes! We understand that managing recipes can be a daunting task, 
              especially when you’re trying to plan your meals for the week. That’s why we’ve created 
              a platform that makes it easy for you to keep track of your favorite recipes and plan 
              your meals ahead of time. With our website, you can easily add new recipes, categorize 
              them by meal type or cuisine, and even create shopping lists based on the ingredients 
              you need. Our user-friendly interface makes it easy to navigate, so you can spend less 
              time managing your recipes and more time enjoying delicious meals with your loved ones. 
              Sign up today and start planning your meals like a pro!

            </p>
               {/* </div>  moving the div here lets us see the background image */}
              {/* <div className="additional-content"> */}
            <h2> How to get a Good Byte: </h2>
            <p> 
              
            </p>
            <img src={Logo} alt="How to get a Good Byte diagram" className="diagram"/>
            <FAQList/>
      </div>
      {/* </div> */}
      
     </div> 
    </div>
    
  );
};
export default Home;