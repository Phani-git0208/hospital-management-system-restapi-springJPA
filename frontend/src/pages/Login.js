import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Login.css";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({ email, password })
      });

      if (!response.ok) {
        alert("Invalid email or password");
        return;
      }

      const data = await response.json();
      console.log("Login success:", data);

      // ✅ FIXED: store correct fields from backend
      localStorage.setItem("token", data.jwt);
      localStorage.setItem("role", data.role);
      localStorage.setItem("userId", data.id);

      alert("Login successful!");

      // ✅ Role-based redirect
      if (data.role === "ADMIN") {
        navigate("/admin/dashboard");
      } else if (data.role === "DOCTOR") {
        navigate("/doctor");
      } else {
        navigate("/patients");
      }

    } catch (error) {
      console.error(error);
      alert("Server error");
    }
  };

  return (
    <div className="login-container">
      <div className="login-card">
        <h2>Welcome Back</h2>

        <input
          type="email"
          placeholder="Email"
          value={email}
          onChange={e => setEmail(e.target.value)}
        />

        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={e => setPassword(e.target.value)}
        />

        <button onClick={handleLogin}>Login</button>

        <p onClick={() => navigate("/auth/signup")}>
          Don’t have an account? <span>Signup</span>
        </p>
      </div>
    </div>
  );
}

export default Login;



