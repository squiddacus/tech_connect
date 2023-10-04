import React from 'react';
import { LinkContainer } from 'react-router-bootstrap';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { UserContext } from '../../context/UserContext';
import { useContext } from 'react';
import Logo from '../../images/G.png';
import LogoutButton from '../../components/form/LogoutButton';


const Header = () => {
  const userContext = useContext(UserContext);
  const currentUser = userContext.currentUser;
  return ( 
    <header>
      {currentUser && currentUser.isAuthenticated && (
        <div id="loggedin" className="option-buttons">You are currently logged in as {currentUser.username} </div>
      )}
      <Navbar className="custom-navbar" expand="lg">
        <Navbar.Brand className="navbar-brand">
          <img src={Logo} alt="Good Bytes logo" className="logo" />
          
        </Navbar.Brand>
		
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="justify-content-center">
            <LinkContainer to="/">
              <Nav.Link className="header-buttons">Home</Nav.Link>
            </LinkContainer>
            {!currentUser || !currentUser.isAuthenticated ? (
            <LinkContainer to="/Login">
              <Nav.Link className="header-buttons">Login</Nav.Link>
            </LinkContainer>
            ) : null}
            {/* {currentUser && currentUser.isAuthenticated &&
 
              <LinkContainer to="/">
              <div>
                    <div><LogoutButton>Logout</LogoutButton></div>
                </div>
            </LinkContainer>
            } */}
              
            {(!currentUser)|| !currentUser.isAuthenticated ? (
            <LinkContainer to="/SignUp">
              <Nav.Link className="header-buttons">Sign Up</Nav.Link>
            </LinkContainer>
            ) : null}
            {(currentUser) && currentUser.isAuthenticated && (
              <NavDropdown title="Options" id="basic-nav-dropdown">
                <LinkContainer to="/MyRecipes">
                  <NavDropdown.Item  className="option-buttons">My Recipes</NavDropdown.Item>
                </LinkContainer>
                <LinkContainer to="/ViewMyMealPlans">
                  <NavDropdown.Item className="option-buttons">My Meal Plans</NavDropdown.Item>
                </LinkContainer>
              </NavDropdown>
            )}
			
          </Nav>
		  
        </Navbar.Collapse>
		<span className="big-bold-font">Good Bytes</span>
		
    {currentUser && currentUser.isAuthenticated &&
 
 <LinkContainer to="/">
 <div>
       <div><LogoutButton>Logout</LogoutButton></div>
   </div>
</LinkContainer>
}
      </Navbar>
    </header>
  );
};
export default Header;
