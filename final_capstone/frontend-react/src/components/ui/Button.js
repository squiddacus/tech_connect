
const Button = ({ children, onClick }) => {
    return (
      <button className="log-out-button" onClick={onClick}>
        {children}
      </button>
    );
  }

  export default Button;