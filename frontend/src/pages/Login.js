import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { loginUser } from "./services/authService";

function getRoleFromToken(token) {
  const payload = JSON.parse(atob(token.split(".")[1]));
  return payload.role;
}

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const handleLogin = async () => {
  try {
    const data = await loginUser(username, password);

    console.log("Backend response:", data);

    // Save JWT token
    localStorage.setItem("token", data.jwt);


     // Get role from token
    const role = getRoleFromToken(data.jwt);
    console.log("Role:", role);

    // Redirect based on role
    if (role === "ADMIN") {
      navigate("/admin/doctor");
    } else if (role === "DOCTOR") {
      navigate("/doctor");
    } else if (role === "PATIENT") {
      navigate("/me");
    } else {
      alert("Unknown role");
    }

    alert("Login successful, token saved");
  } catch (error) {
    console.error("Login failed:", error);
    alert("Login failed");
  }
};


  return (
    <div>
      <input
        placeholder="Username"
        onChange={(e) => setUsername(e.target.value)}
      />

      <input
        type="password"
        placeholder="Password"
        onChange={(e) => setPassword(e.target.value)}
      />

      <button onClick={handleLogin}>Login</button>
    </div>
  );
}

export default Login;

