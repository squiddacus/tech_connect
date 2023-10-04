import React, { useState } from 'react';
const FAQList = () => {
  const faqs = [
    {
      question: 'How does the meal planning app work?',
      answer: 'Our meal planning app allows you to easily plan your meals for the week. You can add your favorite recipes, choose meals from a variety of cuisines, and create a customized meal plan.',
    },
    {
      question: 'Can I personalize my meal plans based on dietary preferences?',
      answer: 'Absolutely! Our app lets you tailor your meal plans to your dietary needs. You can filter recipes by dietary preferences such as vegetarian, vegan, gluten-free, and more.',
    },
    {
        question: 'How do I add my own recipes to the app?',
        answer: 'Adding your recipes is simple. Just navigate to the "My Recipes" section, click "Add New Recipe," and enter the recipe details, ingredients, and instructions.',
      },
      {
        question: 'Can I create a shopping list based on my meal plan?',
        answer: `Yes, you can generate a shopping list with all the ingredients you need for your selected recipes. This feature ensures you won't forget any items while shopping.`,
      },
    // can insert other FAQs, can't think of any more right now
  ];
  const [expandedIndex, setExpandedIndex] = useState(null);
  const toggleQuestion = (index) => {
    if (expandedIndex === index) {
      setExpandedIndex(null);
    } else {
      setExpandedIndex(index);
    }
  };
  return (
    <div >
      <h2 className="faq-heading">Frequently Asked Questions</h2>
      <ul style={{listStyleType: 'none'}}>
      {/*to remove bullets: tried using style="list-style: none;" */}
        {faqs.map((faq, index) => (
          <li
            className={`faq-item ${expandedIndex === index ? 'expanded' : ''}`}
            key={index}
          >
            <h3
              className="faq-question"
              onClick={() => toggleQuestion(index)}
            >
              {faq.question}
              <span className="dropdown-arrow">
                {expandedIndex === index ? ' ▲' : ' ▼'}
              </span>
            </h3>
            {expandedIndex === index && (
              <p className="faq-answer">{faq.answer}</p>
            )}
          </li>
        ))}
      </ul>
    </div>
  );
};
export default FAQList;