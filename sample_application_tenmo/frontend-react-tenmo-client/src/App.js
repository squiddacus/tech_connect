import { UserProvider } from './context/UserContext';
import ErrorBoundary from './ErrorBoundary';
import './assets/css/App.css';
import './assets/css/site.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/Login/Login';
import Home from './pages/Home/Home';
import CreateTransfer from './pages/CreateTransfer/CreateTransfer';
import Transfer from './pages/Transfer/Transfer';
import Transfers from './pages/Transfer/Transfers';
import Pendings from './pages/Pending/Pendings'
import Account from './pages/Account/Account';
import Register from './pages/Login/Register';

function App() {
  return (
    <div id="app">
      <ErrorBoundary>
        <UserProvider>
          <BrowserRouter>          
            <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/Login" element={<Login />} />
              <Route path="/Register" element={<Register />} />
              <Route path="/Transfer/:id" element={<Transfer  />} />
              <Route path="/Transfers" element={<Transfers  />} />
              <Route path="/Pending" element={<Pendings  />} />
              <Route path="/New" element={<CreateTransfer  />} />
              <Route path="/Account" element={<Account  />} />
            </Routes>
          </BrowserRouter>
        </UserProvider>
      </ErrorBoundary>
    </div>
  );
}

export default App;
