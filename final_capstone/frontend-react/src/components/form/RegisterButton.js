import { UserContext } from "../../context/UserContext";
import { useContext } from "react";
import Button from "../ui/Button";
import '../../pages/Login/Login.css'



const RegisterButton = () => {
  const userContext = useContext(UserContext);
  const { setRegisteredUser } = useContext(UserContext);
  const registeredUser = userContext.registeredUser;
    if (!registeredUser || !registeredUser.isAuthenticated) {
      return <></>;
    }
  
    const handleSignup = () => {
      localStorage.setItem('token');
      localStorage.setItem('user');        
      setRegisteredUser({});
    }
    return (
      <Button className="button-container" onClick={handleSignup}>SignUp {registeredUser.username}</Button>
    );
  }

  export default RegisterButton;