// import React, { useState, useContext } from 'react';
// import { Button, ButtonGroup } from 'react-bootstrap';
// import Form from 'react-bootstrap/Form';
// import Layout from "../../components/ui/Layout";
// import { UserContext } from "../../context/UserContext";
// import { useNavigate } from "react-router-dom";
// import { registerUser } from '../../api/UserApi'; // Import the registerUser function
// import RegisterButton from '../../components/form/RegisterButton';
// //import './Signup.css'; // Update the CSS file name accordingly
// const Signup = () => {
//   const navigate = useNavigate();
//   const userContext = useContext(UserContext);
//   const { setCurrentUser } = userContext;
//   const [username, setUsername] = useState('');
//   const [password, setPassword] = useState('');
//   const doSetUsername = (event) => {
//     setUsername(event.target.value);
//   };
//   const doSetPassword = (event) => {
//     setPassword(event.target.value);
//   };
//   const handleCancel = () => {
//     setPassword('');
//     navigate("/");
//   };
//   const handleSignup = async (event) => {
//     event.preventDefault();
//     const newUser = { 'username': username, 'password': password };
//     const registeredUser = await registerUser(newUser);
//     if (registeredUser) {
//       registeredUser.user.isAuthenticated = true;
//       localStorage.setItem('user', JSON.stringify(registeredUser.user));
//       localStorage.setItem('token', registeredUser.token);
//       setCurrentUser(registeredUser.user);
//       navigate("/");
//     }
//   };
//   return (
//     <Layout>
//       <div >
//         <Form onSubmit={handleSignup}>
//           <div>
//             <Form.Group controlId="formBasicUsername">
//               <Form.Label>Username</Form.Label>
//               <Form.Control type="text" placeholder="Enter username" value={username} onChange={doSetUsername} />
//             </Form.Group>
//           </div>
//           <div>
//             <Form.Group controlId="formBasicPassword">
//               <Form.Label>Password</Form.Label>
//               <Form.Control type="password" placeholder="Password" value={password} onChange={doSetPassword} />
//             </Form.Group>
//           </div>
//           <ButtonGroup>
//             <Button onClick={handleSignup} type="submit">
//               Sign Up
//             </Button>
//             <Button onClick={handleCancel}>
//               Cancel
//             </Button>
//           </ButtonGroup>
//         </Form>
//         {registerUser && registerUser.isAuthenticated &&
//                 <div className="app">
//                     <div><RegisterButton className="button-container"></RegisterButton></div>
//                 </div>

//             }
//       </div>
//     </Layout>
//   );
// }
// export default Signup;



import React, { useState, useContext } from 'react';
import { Button, ButtonGroup } from 'react-bootstrap';
import Form from 'react-bootstrap/Form';
import Layout from "../../components/ui/Layout";
import { UserContext } from "../../context/UserContext";
import { useNavigate } from "react-router-dom";
import { registerUser } from '../../api/UserApi'; // Import the registerUser function
import RegisterButton from '../../components/form/RegisterButton';
import {loginUser}    from '../../api/UserApi'
import Login from '../Login/Login';

// import './Signup.css'; // Update the CSS file name accordingly
const Signup = ({children}) => {
  const navigate = useNavigate();
  const userContext = useContext(UserContext);
  const { setCurrentUser } = userContext;
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [role, setRole] = useState('user'); // Default role is 'user'
  const doSetUsername = (event) => {
    setUsername(event.target.value);
  };
  const doSetPassword = (event) => {
    setPassword(event.target.value);
  };
  const doSetConfirmPassword = (event) => {
    setConfirmPassword(event.target.value);
  };
  const doSetRole = (event) => {
    setRole(event.target.value);
  };
  const handleCancel = () => {
    setUsername('');
    setPassword('');
    setConfirmPassword('');
    setRole('');
    navigate("/");
  };
  const handleSignup = async (event) => {
    event.preventDefault();
    if (password !== confirmPassword) {
      // Handle password mismatch
      alert("passwords don't match!")
      return;
    }
    const newUser = {
      'username': username,
      'password': password,
      'confirmPassword': confirmPassword,
      'role': role
    };
    try {
      const registeredUser = await registerUser(newUser);
      const userLogin = { 'username': newUser.username, 'password': newUser.password };
      const loggedInUser = await loginUser(userLogin);

      setCurrentUser(registerUser.user);
      // handleLogin();
      console.log(`User ${userLogin.username} was logged in`)
      navigate('/Login');
  } catch (error) {
      console.error("Error registering user:", error);
  }
  };
  return (
    <Layout>
      <div className="app">
        <Form className="signup-form" onSubmit={handleSignup}>
          <div className="input-container">
            <Form.Group controlId="formBasicUsername">
              <Form.Label>Username</Form.Label>
              <Form.Control type="text" placeholder="Enter username" value={username} onChange={doSetUsername} />
            </Form.Group>
          </div>
          <div className="input-container">
            <Form.Group controlId="formBasicPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control type="password" placeholder="Password" value={password} onChange={doSetPassword} />
            </Form.Group>
          </div>
          <div className="input-container">
            <Form.Group controlId="formBasicConfirmPassword">
              <Form.Label>Confirm Password</Form.Label>
              <Form.Control type="password" placeholder="Confirm Password" value={confirmPassword} onChange={doSetConfirmPassword} />
            </Form.Group>
          </div>
          <div className="input-container">
            <Form.Group controlId="formBasicRole">
              <Form.Label>Role</Form.Label>
              <Form.Control as="select" value={role} onChange={doSetRole}>
                <option value="user">User</option>
                <option value="admin">Admin</option>
              </Form.Control>
            </Form.Group>
          </div>
          <ButtonGroup className="button-container">
            <Button className="button-container" onClick={handleSignup} type="submit">
              Sign Up
            </Button>
            <Button className="button-container" onClick={handleCancel}>
              Cancel
            </Button>
          </ButtonGroup>
        </Form>
        {registerUser && registerUser.isAuthenticated &&
                <div className="app">
                    <div><RegisterButton className="button-container"></RegisterButton></div>
                </div>

            }
            {children}
      </div>
    </Layout>
  );
}
export default Signup;