import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "./Dashboard.css";

function Dashboard() {
  const [name, setName] = useState("");
  const [appointments, setAppointments] = useState([]);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();
  const token = localStorage.getItem("token");

  useEffect(() => {
    if (!token) {
      navigate("/auth/login");
      return;
    }

    // Load profile
    fetch(`${process.env.REACT_APP_API_BASE_URL}/patient/me`, {
      headers: { Authorization: `Bearer ${token}` }
    })
      .then(res => {
        if (!res.ok) throw new Error("Unauthorized");
        return res.json();
      })
      .then(data => setName(data.name))
      .catch(() => navigate("/auth/login"));

    // Load appointments
    fetch(`${process.env.REACT_APP_API_BASE_URL}/patient/appointments`, {
      headers: { Authorization: `Bearer ${token}` }
    })
      .then(res => res.json())
      .then(data => setAppointments(data))
      .catch(() => {})
      .finally(() => setLoading(false));
  }, [token, navigate]);

  const handleLogout = () => {
    localStorage.clear();
    navigate("/auth/login");
  };

  return (
    <div className="dashboard">
      <div className="dashboard-container">
        <h1>Dashboard</h1>
        <p className="welcome-text">Welcome back, {name || "Patient"}</p>

        {/* Quick Actions */}
        <div className="card">
          <h3>Quick Actions</h3>
          <div className="actions">
            <button className="btn" onClick={() => navigate("/appointments")}>
              View Appointments
            </button>
            <button className="btn" onClick={() => navigate("/book")}>
              Book Appointment
            </button>
            <button className="btn btn-secondary" onClick={() => navigate("/me")}>
              Edit Profile
            </button>
            <button className="btn btn-danger" onClick={handleLogout}>
              Logout
            </button>
          </div>
        </div>

        {/* Upcoming Appointments */}
        <div className="card">
          <h3>Upcoming Appointments</h3>

          {loading ? (
            <p className="empty-text">Loading...</p>
          ) : appointments.length === 0 ? (
            <p className="empty-text">You don’t have any appointments yet.</p>
          ) : (
            appointments.slice(0, 3).map(a => (
              <div key={a.id} className="appointment-item">
                <p><b>Doctor:</b> {a.doctorName}</p>
                <p><b>Date:</b> {a.appointmentDate}</p>
                <p><b>Time:</b> {a.appointmentTime}</p>
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  );
}

export default Dashboard;




