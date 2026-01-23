import { useState } from "react";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleLogin = async () => {
    const response = await fetch("http://localhost:8080/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        username: username,
        password: password
      })
    });

    if (!response.ok) {
      alert("Login failed");
      return;
    }

    const data = await response.json();

    console.log("Login response:", data);

    // Save token in browser
    localStorage.setItem("token", data.token);

    alert("Login successful!");
  };

  return (
    <div>
      <h2>Login</h2>

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

      <button onClick={handleLogin}>Login</button>
    </div>
  );
  

}

export default Login;
