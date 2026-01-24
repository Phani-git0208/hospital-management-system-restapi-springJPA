import { useEffect, useState } from "react";

function Doctors() {
  const [doctors, setDoctors] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/admin/doctor", {
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
    <div>
      <h2>Doctors (Protected)</h2>
      {doctors.map(d => (
        <p key={d.id}>{d.name}</p>
      ))}
    </div>
  );
}

export default Doctors;
