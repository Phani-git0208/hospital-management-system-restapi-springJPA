import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Signup() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const handleSignup = async () => {
    const response = await fetch("http://localhost:8080/auth/signup", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        username,
        password
      })
    });

    if (!response.ok) {
      alert("Signup failed");
      return;
    }

    const data = await response.json();
    console.log("Signup response:", data);

    alert("Signup successful! Please login.");

    // After signup, redirect to login page
    navigate("/auth/login");
  };

  return (
    <div>
      <h2>Signup</h2>

      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={e => setUsername(e.target.value)}
      />
      <br />

      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={e => setPassword(e.target.value)}
      />
      <br />

      <button onClick={handleSignup}>Signup</button>
    </div>
  );
}

export default Signup;
