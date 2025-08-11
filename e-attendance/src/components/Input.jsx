import'./Input.css';
import { EyeInvisibleOutlined, EyeOutlined } from '@ant-design/icons';
import {useState} from 'react';

function Input({type='text', name, error,  showPassword, setShowPassword, placeholder, value, onChange, ...rest}){
    
    
    const isPasswordField=name.toLowerCase()==="password" || name.toLowerCase()==="repassword";
    const togglePassword=()=>setShowPassword((val)=>!val);
    return(
        <div 
        className='input-container'>
        <input
        name={name}
        className="custom-input" 
        type={isPasswordField && !showPassword?"password":"text"} 
        placeholder={placeholder} 
        value={value} 
        onChange={onChange}
        {...rest}
        
        />
        
        {isPasswordField && (
        <span
        
        className='password-toggle'
          onClick={togglePassword}
        >
          {showPassword ? <EyeInvisibleOutlined /> : <EyeOutlined />}
        </span>
      )}
        <div className='error-text'>
            {error?error:'\u00A0'}
        </div>
        </div>
    );
}
export default Input;