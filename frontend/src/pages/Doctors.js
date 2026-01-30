import { useEffect, useState } from "react";

function Doctors() {
  const [doctors, setDoctors] = useState([]);

  useEffect(() => {
    fetch(`${process.env.REACT_APP_API_BASE_URL}/admin/doctors`, {
      headers: {
        Authorization: `Bearer ${localStorage.getItem("token")}`
      }
    })
      .then(res => {
        if (!res.ok) throw new Error("Unauthorized");
        return res.json();
      })
      .then(data => setDoctors(data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div style={{ padding: "30px" }}>
      <h1>Doctors</h1>

      {doctors.map(d => (
        <div
          key={d.id}
          style={{
            border: "1px solid #ccc",
            borderRadius: "10px",
            padding: "15px",
            marginBottom: "15px",
            boxShadow: "2px 2px 8px rgba(0,0,0,0.1)"
          }}
        >
          <h3>👨‍⚕️ {d.user?.name}</h3>

          <p><strong>Specialization:</strong> {d.specialization}</p>
          <p><strong>Experience:</strong> {d.experienceYears} years</p>
          <p><strong>Email:</strong> {d.user?.email}</p>
          <p><strong>Phone:</strong> {d.phone}</p>
        </div>
      ))}
    </div>
  );
}

export default Doctors;

