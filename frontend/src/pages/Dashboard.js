import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Dashboard.css";

function Dashboard() {
  const [name, setName] = useState("");
  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  useEffect(() => {
    fetch("http://localhost:8080/patient/me", {
      headers: { Authorization: `Bearer ${token}` }
    })
      .then(res => res.json())
      .then(data => setName(data.name))
      .catch(() => {});
  }, [token]);

  const handleLogout = () => {
    localStorage.clear();
    navigate("/auth/login");
  };

  return (
    <div className="dashboard">
      <div className="dashboard-container">
        <h1>Dashboard</h1>
        <p className="welcome-text">Welcome back, {name}</p>

        {/* Actions */}
        <div className="card">
          <h3>Quick Actions</h3>
          <div className="actions">
            <button className="btn" onClick={() => navigate("/appointments")}>
              View Appointments
            </button>
            <button className="btn" onClick={() => navigate("/book")}>
              Book Appointment
            </button>
            <button className="btn btn-secondary" onClick={() => navigate("/myprofile")}>
              Edit Profile
            </button>
            <button className="btn btn-danger" onClick={handleLogout}>
              Logout
            </button>
          </div>
        </div>

        {/* Upcoming */}
        <div className="card">
          <h3>Upcoming Appointments</h3>
          <p className="empty-text">You don’t have any appointments yet.</p>
        </div>
      </div>
    </div>
  );
}

export default Dashboard;



