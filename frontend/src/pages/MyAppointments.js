import { useEffect, useState } from "react";

function MyAppointments() {
  const [appointments, setAppointments] = useState([]);
  const token = localStorage.getItem("token");

  useEffect(() => {
    fetch("http://localhost:8080/patient/appointments", {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then(res => res.json())
      .then(data => setAppointments(data))
      .catch(err => console.error("Failed to load appointments", err));
  }, []);

  return (
    <div style={{ padding: "20px" }}>
      <h2>My Appointments</h2>

      {appointments.length === 0 && <p>No appointments yet.</p>}

      {appointments.map(a => (
        <div key={a.id} style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}>
          <p><b>Doctor:</b> {a.doctorName}</p>
          <p><b>Date:</b> {a.appointmentDate}</p>
          <p><b>Time:</b> {a.appointmentTime}</p>
          <p><b>Reason:</b> {a.reason}</p>
        </div>
      ))}
    </div>
  );
}

export default MyAppointments;
