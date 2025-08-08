import'./Input.css';

function Input({type='text', name, error,  placeholder, repassword, value, onChange, ...rest}){
    return(
        <div style={{marginBottom: '10px'}}>
        <input 
        name={name}
        className="custom-input" 
        type={type} 
        placeholder={placeholder} 
        value={value} 
        repassword={repassword}
        onChange={onChange}
        {...rest}
        />
        {error && <small style={{color: 'red'}}>{error}</small>}
        </div>
    );
}
export default Input;