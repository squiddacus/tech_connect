import { useState } from 'react';
import { Button, ButtonGroup } from 'react-bootstrap';
import Form from 'react-bootstrap/Form';
import Layout from "../../components/ui/Layout";
import {registerUser}    from '../../api/UserApi'
import { useNavigate } from "react-router-dom";


const Register = ({ children }) => {
    const navigate = useNavigate();
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
    };

    const handleRegister = (event) => {
        if (event !== undefined) event.preventDefault();
        const registerCurrentUser = async () => {
            const userToCreate = { 'username': username, 'password': password };
            const newUser = await registerUser(userToCreate);
            console.log(newUser);
            navigate("/");
        };
        registerCurrentUser();
    };
    return (
        <Layout>
            
                <Form onSubmit={handleRegister}>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Username</Form.Label>
                        <Form.Control type="text" placeholder="Enter username"
                            value={username} onChange={doSetUsername} />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" placeholder="Password"
                            value={password} onChange={doSetPassword} />
                    </Form.Group>
                    <ButtonGroup>
                        <Button variant="primary" type="submit">
                            Register
                        </Button>
                        <Button variant="secondary" onClick={handleCancel}>
                            Cancel
                        </Button>
                    </ButtonGroup>
                </Form>            
            {children}
        </Layout>
    );
}
export default Register;