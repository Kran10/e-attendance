import {useState} from 'react';
import Input from './Input';

function Register(){
    const[user, setUser]=useState({username: "", email: "", password:"" ,repassword:""});
    const [message, setMessage]=useState("");

    const handleChange=e=>{
        setUser({...user, [e.target.name]:e.target.value});
    };
    const handleSubmit=async e=>{
        e.preventDefault();
        try{
            const res=await axios.post('http://localhost:8080/api/users/register', user);
            setMessage("Registration successful");
        }catch(err){
            setMessage("Error: "+err.response?.data||"Could not register");
        }
    };
    return(
        
        <div className='register-container'>
            <div className="form-wrapper">
                <h2>Register</h2>
            <form className="register-form" onSubmit={handleSubmit}>
                <Input 
                name="username"
                placeholder="Username"
                value={user.username}
                onChange={handleChange}/>

                <Input 
                name="email"
                type="email"
                placeholder="Email"
                value={user.email}
                onChange={handleChange}/>

                <Input 
                name="password"
                type="password"
                placeholder="Password"
                value={user.password}
                onChange={handleChange}/>

                <Input 
                name="repassword"
                type="password"
                placeholder="Confirm Password"
                value={user.repassowrd}
                onChange={handleChange}/>

                <button className="custom-button">Submit</button>

            </form>
            
            {message && <p className="message">{message}</p>}
            </div>
        </div>
    );

}
export default Register;