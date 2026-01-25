import { useEffect, useState } from "react";
import axios from "axios";

function Doctordashboard() {
  const [doctor, setDoctor] = useState(null);

  useEffect(() => {
    const token = localStorage.getItem("token"); // or whatever key you use
    const doctorId = localStorage.getItem("userId"); // you said JWT contains userId

    axios.get(`http://localhost:8080/doctor/${doctorId}`, {
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
    <div>
      <h2>Doctor Dashboard</h2>
      <p><b>ID:</b> {doctor.id}</p>
      <p><b>Name:</b> {doctor.name}</p>
      <p><b>Specialization:</b> {doctor.specialization}</p>
      <p><b>Email:</b> {doctor.email}</p>
      <p><b>Phone:</b> {doctor.phone}</p>
      <p><b>Experience:</b> {doctor.experienceYears} years</p>
    </div>
  );
}

export default Doctordashboard;
