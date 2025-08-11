 import {useState} from 'react';
import {useForm} from 'react-hook-form';
import axios from 'axios';
import Input from './Input';
import { Link } from 'react-router-dom';


function Register(){
    const{
        register,
        handleSubmit,
        formState:{errors},
        trigger,
        watch
    }=useForm();

    const password=watch('password');
    const confirmPassword=watch("repassword");
    
    const onSubmit=async (data)=>{
        try{
            const res=await axios.post('http://localhost:8080/api/users/register', data);
            alert("Registration successful");
        }catch(err){
            alert("Error: "+err.response?.data||"Could not register");
        };
    }
    const[user, setUser]=useState({username: "", email: "", password:"" ,repassword:""});
    const [message, setMessage]=useState("");

    const handleChange=e=>{
        setUser({...user, [e.target.name]:e.target.value});
    };
    const[showPassword,setShowPassword]=useState(false);
    
    return(
        
        <div className='register-container'>
            <div className="form-wrapper">
                <h2>Register</h2>
            <form className="register-form" onSubmit={handleSubmit(onSubmit)}>
                <Input 
                name="username"
                placeholder="Username"
                {...register("username",{
                    required: "Username is required",
                    onblur:()=>trigger("username"),
                })}
                error={errors.username?.message}
                value={user.username}
                onChange={handleChange}/>

                <Input 
                name="email"
                type="email"
                showPassword={showPassword}
                setShowPassword={setShowPassword}
                placeholder="Email"
                {...register("email",{
                    required: "Email is required",
                    pattern:{
                        value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                        message: "Invalid email format"
                    },
                    onBlur:()=>trigger("email"),
                })}
                error={errors.email?.message}
                value={user.email}
                onChange={handleChange}/>

                <Input 
                name="password"
                type="password"
                showPassword={showPassword}
                setShowPassword={setShowPassword}
                placeholder="Password"
                {...register("password",{
                    required: "Password is required",
                    minLength: {
                        value:6,
                        message: "Password must be at least 6 characters"
                    },
                    onBlur:()=>trigger("password")
                })}
                error={errors.password?.message}
                value={user.password}
                onChange={handleChange}/>

                <Input 
                name="repassword"
                type="password"
                {...register("repassword",{
                    required: "Please confirm your password",
                    onBlur: ()=>trigger("repassword"),
                })}
                showPassword={showPassword}
                setShowPassword={setShowPassword}
                error={errors.repassword?.message}
                placeholder="Confirm Password"
                value={user.repassword}
                onChange={handleChange}
                />
                {confirmPassword && password && (
                <p style={{ color: password === confirmPassword ? 'darkgreen' : 'red', fontWeight: 'bold'}}>
                    {password === confirmPassword ? 'Password is matched' : 'Passwords do not match'}
                </p>
                )}

                <button className="custom-button">Submit</button>
                <p className="info-message">Already have an account? <Link to="/login" style={{color: 'darkred', textDecoration: 'none', fontWeight:'bold'}}>Login</Link></p>

            </form>

            {message && <p className="message">{message}</p>}
            </div>
        </div>
    );
}

export default Register;