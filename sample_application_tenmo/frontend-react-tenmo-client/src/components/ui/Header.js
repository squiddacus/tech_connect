import { Container, Nav, NavDropdown, Navbar } from "react-bootstrap";
import { LinkContainer } from "react-router-bootstrap";
import { UserContext } from "../../context/UserContext";
import { useContext } from "react";

import Logo from '../../images/tenmo_icon.png'

const Header = () => {
    const userContext = useContext(UserContext);
    const currentUser = userContext.currentUser;

	return (
		<>
			<header>	
				<Nav>
					<Navbar bg="light" variant="light" expand="lg">
						<Container>
							<Navbar.Brand>
							<img src={Logo} alt="Blue Moon Logo"/>
							</Navbar.Brand>
							<Navbar.Toggle aria-controls="basic-navbar-nav" />
							<Navbar.Collapse id="basic-navbar-nav">
								<Nav className="justify-content-center">
									<LinkContainer to="/">
										<Nav.Link>Home</Nav.Link>
									</LinkContainer>
									<NavDropdown title="Login" id="basic-nav-dropdown">
											<LinkContainer to="/Login">
												<NavDropdown.Item>Login</NavDropdown.Item>
											</LinkContainer>
											<LinkContainer to="/Register">
												<NavDropdown.Item>Register</NavDropdown.Item>
											</LinkContainer>
									</NavDropdown>
								</Nav>
							</Navbar.Collapse>
						</Container>
					</Navbar>
				</Nav>
			</header>
		</>
	);
};

export default Header;
