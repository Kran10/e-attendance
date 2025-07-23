import Register from './components/Register';
import Login from './components/Login';
import {Routes, Route, Link} from 'react-router-dom';
import {useLocation} from 'react-router-dom';

function App(){
  const location=useLocation();
  const isRegisterPage=location.pathname==='/register';
  return(
    <div className={isRegisterPage?'register-bg':''}>
      <h1>E-Attendance</h1>
      <nav className="menu-nav">
        <Link to="/">Home</Link>
        <Link to="/register">Register</Link>
        <Link to="/login">Login</Link>
      </nav>
      <Routes>
        <Route path="/" element={<h2>Welcome to E-Attendance</h2>}/>
        <Route path="/register" element={<Register/>}/>
        <Route path="/login" element={<Login/>}/>

      </Routes>
      
    </div>
  );
}
export default App;