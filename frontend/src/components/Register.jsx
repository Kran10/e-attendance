import { useState } from "react";
import { useForm } from "react-hook-form";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import Input from "./Input"; // your custom Input component

function Register() {
  const {
    register,
    handleSubmit,
    formState: { errors },
    trigger,
    watch,
  } = useForm();

  const password = watch("password");
  const confirmPassword = watch("repassword");

  const [user, setUser] = useState({
    username: "",
    email: "",
    password: "",
    repassword: "",
  });

  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false); //  loading state
  const [showPassword, setShowPassword] = useState(false);

  const navigate = useNavigate(); //  for redirect

  const handleChange = (e) => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const onSubmit = async (data) => {
    setLoading(true);
    try {
        const payload={
            username: data.username,
            email:data.email,
            password:data.password,
        };
      const res=await axios.post("http://localhost:8080/api/users/register", payload);
      console.log(res.data);
      setMessage("✅ Registration successful");
      setTimeout(() => {
        navigate("/login"); // redirect after success
      }, 1500);
    } catch (err) {
      setMessage("❌ " + (err.response?.data || "Could not register"));
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="register-container">
      <div className="form-wrapper">
        <h2>Register</h2>
        <form className="register-form" onSubmit={handleSubmit(onSubmit)}>
          <Input
            name="username"
            placeholder="Username"
            {...register("username", {
              required: "Username is required",
              onBlur: () => trigger("username"),
            })}
            error={errors.username?.message}
           
          />

          <Input
            name="email"
            type="email"
            placeholder="Email"
            {...register("email", {
              required: "Email is required",
              pattern: {
                value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
                message: "Invalid email format",
              },
              onBlur: () => trigger("email"),
            })}
            error={errors.email?.message}
           
          />

          <Input
            name="password"
            type="password"
            placeholder="Password"
            showPassword={showPassword}
            setShowPassword={setShowPassword}
            {...register("password", {
              required: "Password is required",
              minLength: { value: 6, message: "Min 6 characters" },
              onBlur: () => trigger("password"),
            })}
            error={errors.password?.message}
           
          />

          <Input
            name="repassword"
            type="password"
            showPassword={showPassword}
            setShowPassword={setShowPassword}
            placeholder="Confirm Password"
            {...register("repassword", {
              required: "Please confirm your password",
              onBlur: () => trigger("password"),
            })}
            error={errors.repassword?.message}
           
          />

          {confirmPassword && password && (
            <p
              style={{
                color: password === confirmPassword ? "darkgreen" : "red",
                fontWeight: "bold",
              }}
            >
              {password === confirmPassword
                ? "Password is matched"
                : "Passwords do not match"}
            </p>
          )}

          <button
            type="submit"
            className="custom-button"
            disabled={loading}
          >
            {loading ? "⏳ Registering..." : "Submit"}
          </button>

          <p className="info-message">
            Already have an account?{" "}
            <Link
              to="/login"
              style={{ color: "darkred", textDecoration: "none", fontWeight: "bold" }}
            >
              Login
            </Link>
          </p>
        </form>

        {message && <p className="message">{message}</p>}
      </div>
    </div>
  );
}

export default Register;
