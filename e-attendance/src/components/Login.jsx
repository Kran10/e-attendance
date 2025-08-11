import {useForm} from 'react-hook-form';
import Input from './Input';
import { Link } from 'react-router-dom';
import {useState} from 'react';



function Login(){
    const [showPassword, setShowPassword]=useState(false);

    const toggleBtn=()=>{
        setVisible((val)=>!val);
    }
    
    const {
        register,
        handleSubmit,
        formState:{errors},
        trigger
    }=useForm();

    const onSubmit=async(data)=>{
        try{
            const response=await axios.post("http://localhost:8080/api/users/login", data)
            alert("Login successful");
        }catch(error){
            alert("Login failed: "+(error.response?.data||"Unknown error"));
        }
    };
    return(
       <div className="register-container">
        <div className="form-wrapper">
            <h2>Login</h2>
            <form onSubmit={handleSubmit(onSubmit)} className="login-form">
                <Input 
                name="email"
                type="email"
                placeholder="Email"
                {...register("email",{
                    required: "Email is required",
                    pattern:{
                        value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                        message: "Invalid email format"
                    },
                    onBlur:()=>trigger("email")
                })}
                error={errors.email?.message}/>

                <Input 
                name="password"
                type="password" 
                placeholder="Password"
                showPassword={showPassword}
                setShowPassword={setShowPassword}
                {...register("password",{
                    minLength:{
                        value: 6,
                        message: "Password must be at least 6 characters",
                    },
                    required: "Password is required",
                    onBlur:()=>trigger("password")
                })}
                error={errors.password?.message}
                />
                
                <button className="custom-button" type="Submit">Login</button>
            </form>
                <p className="info-message">Don't have account?<Link to="/register"style={{color: 'darkred', textDecoration: 'none', fontWeight:'bold'}}>Register</Link></p>
        </div>
       </div>
    );
}
export default Login;