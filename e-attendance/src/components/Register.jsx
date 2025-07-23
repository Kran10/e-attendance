import {useState} from 'react';
import Input from './Input';
// import axios from 'axios';

function Register(){
    const[user, setUser]=useState({username: "", email: "", password:""});
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
            <div className="inner-box">
                <h2>Register</h2>
            <form className="register-form" onSubmit={handleSubmit}>
                {/* <input name="username" placeholder='Username' onChange={handleChange} required/><br/><br/>
                <input name="email" type="email" placeholder="Email" onChange={handleChange} required/><br/><br/>
                <input name="password" type="password" placeholder='password' onChange={handleChange} required/><br/><br/>
                <input name="password" type="password" placeholder='re-enter password' onChange={handleChange} required/><br/><br/> */}
                <Input placeholder={"Name"}/>
                <Input type="email" placeholder="Email"/>
                <Input type="password" placeholder="Password"/>
                <Input type="password" placeholder="Re-enter password"/>
                <Input type="button" value="Register"/>
            </form>
            </div>
            <p>{message}</p>
        </div>
    );

}
export default Register;