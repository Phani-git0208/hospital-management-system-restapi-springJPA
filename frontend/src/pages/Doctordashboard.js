import { useEffect, useState } from "react";
import axios from "axios";

function Doctordashboard() {
  const [doctor, setDoctor] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token");

    axios.get(`${process.env.REACT_APP_API_BASE_URL}/doctor/profile`, {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
    .then(res => {
      setDoctor(res.data);
    })
    .catch(err => {
      console.error("Error fetching doctor", err);
    });
  }, []);

  if (!doctor) return <p>Loading...</p>;

  return (
    <div style={{ padding: "30px" }}>
      <h2>Doctor Dashboard</h2>

      <p><b>Name:</b> {doctor.user?.name}</p>
      <p><b>Email:</b> {doctor.user?.email}</p>
      <p><b>Specialization:</b> {doctor.specialization}</p>
      <p><b>Phone:</b> {doctor.phone}</p>
      <p><b>Experience:</b> {doctor.experienceYears} years</p>
    </div>
  );
}

export default Doctordashboard;
