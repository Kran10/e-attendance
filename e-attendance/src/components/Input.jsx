import'./Input.css';

function Input({type='text', name, placeholder, value, onChange}){
    return(
        <input 
        name={name}
        className="custom-input" 
        type={type} 
        placeholder={placeholder} 
        value={value} 
        onChange={onChange}
        />
    );
}
export default Input;