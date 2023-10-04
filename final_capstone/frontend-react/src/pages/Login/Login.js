import { useState,useContext } from 'react';
import { Button, ButtonGroup } from 'react-bootstrap';
import Form from 'react-bootstrap/Form';
import Layout from "../../components/ui/Layout";
import { UserContext } from "../../context/UserContext";
import LogoutButton from '../../components/form/LogoutButton';
import { useNavigate } from "react-router-dom";
import {loginUser}    from '../../api/UserApi'
//import Home from '../Home/Home';
import './Login.css';


const Login = ({ children }) => {
    const navigate = useNavigate();        
    const { setCurrentUser } = useContext(UserContext);
    const userContext = useContext(UserContext);
    const currentUser = userContext.currentUser;
  

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const doSetUsername = (event) => {
        setUsername(event.target.value);
    };
    const doSetPassword = (event) => {
        setPassword(event.target.value);
    };


    const handleCancel = () => {
        setPassword('');
        navigate("/");
    };

    const handleLogin = (event) => {
        if (event !== undefined) event.preventDefault();
        const loginCurrentUser = async () => {
            const userLogin = { 'username': username, 'password': password };
            const loggedInUser = await loginUser(userLogin);
            if (loggedInUser) {                  
                loggedInUser.user.isAuthenticated = true;
                localStorage.setItem('user', JSON.stringify(loggedInUser.user));
                localStorage.setItem('token', loggedInUser.token);        
                setCurrentUser(loggedInUser.user);
                navigate("/");
            }
        };
        loginCurrentUser();
    };
    return (
        <Layout>
            <div className="app"> 
            {(!currentUser || !currentUser.isAuthenticated) &&
                <Form className="login-form" onSubmit={handleLogin}>
                    <div className="input-container">
                    <Form.Group  controlId="formBasicEmail">
                        <Form.Label>Username</Form.Label>
                        <Form.Control type="text" placeholder="Enter username"
                            value={username} onChange={doSetUsername} />
                    </Form.Group>
                    </div>
                    <div className="input-container">
                    <Form.Group  controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password"
                            value={password} onChange={doSetPassword} />
                    </Form.Group>
                    </div>
                    <ButtonGroup className="button-container">
                        <Button className="button-container"  onClick={handleLogin} type="submit">
                            Login
                        </Button>
                        <Button className="button-container" onClick={handleCancel}>
                            Cancel
                        </Button>
                    </ButtonGroup>
                </Form>
            }
            {currentUser && currentUser.isAuthenticated &&
                <div className="app">
                    <div><LogoutButton className="log-out-button"></LogoutButton></div>
                </div>

            }
            {children}
            </div>
        </Layout>
    );
}
export default Login;