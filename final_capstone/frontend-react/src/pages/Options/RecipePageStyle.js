import styled from "styled-components";
// run command: npm i styled-components

//import { PageHeader } from "../../components/PageHeader";
//import { BasePageContainer } from "../../styles/global";
//import { FaChevronLeft } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

export const BasePageContainer = styled.main`
  width: 100%;
  max-width: 1000px;
  margin: 0 auto;
  margin-top: 60px;
  padding-left: 10px;
  padding-right: 10px;

  @media (max-width: 1000px) {
    max-width: 800px;
  }
`

export const PageHeaderContainer = styled.section`
  h1 {
    font-family: "Rakkas", serif;
    font-size: 48px;
    color: #333333;
    letter-spacing: 2px;
  }

  p {
    font-size: 20px;
  }

  div {
    display: flex;
    align-items: center;
    gap: 10px;
  }

  svg {
    color: #FEE1B7;
    cursor: pointer;
  }
`;



export const RecipeContainer = styled(BasePageContainer)`
  margin-bottom: 60px;

  h3 {
    color: black;
    font-size: 30px;
    margin-top: 60px;
    margin-bottom: 30px;
  }

  h4 {
    font-weight: 500;
    font-size: 20px;
    line-height: 40px;
  }
`;

export function PageHeader({ title, subtitle, ...rest }) {
  const navigate = useNavigate();

  function handleGoBack() {
    navigate(-1);
  }

  return (
    <PageHeaderContainer {...rest}>
      <div>
        <h1>{title}</h1>
      </div>
      <p>{subtitle}</p>
    </PageHeaderContainer>
  );
}

export const RecipeOverviewContainer = styled.section`
  display: flex;
  height: 350px;
  width: 100%;
  border-radius: 20px;
  overflow: hidden;
  background: #FFF0D7;
  margin-top: 60px;

  img {
    width: 400px;
    height: 100%;
    object-fit: cover;
  }

  div {
    font-size: 20px;
    padding: 30px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 20px;
  }

  strong {
    color: ${(props) => props.theme.primary};
  }

  @media (max-width: 750px) {
    img {
      width: 300px;
    }
  }
  @media (max-width: 665px) {
    img {
      width: 200px;
    }
  }
  @media (max-width: 550px) {
    flex-direction: column;
    height: auto;
    margin-top: 10px;
    img {
      width: 100%;
      height: 200px;
    }
  }
`;

export const RecipeIngredients = styled.ul`
  font-size: 22px;
  display: flex;
  flex-direction: column;
  gap: 15px;
  margin-left: 20px;

  li::marker,
  strong {
    color: ${(props) => props.theme.green};
  }
`;

export const RecipeInstructions = styled.ul`
  font-size: 22px;
  display: flex;
  flex-direction: column;
  gap: 15px;
  margin-left: 20px;

  li::marker,
  strong {
    color: ${(props) => props.theme.green};
  }
`;

export const RecipePageHeader = styled(PageHeader)`
  @media (max-width: 550px) {
    h1 {
      font-size: 30px;
      text-align: center;
    }
  }
`;