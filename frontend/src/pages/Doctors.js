import { useEffect, useState } from "react";

function Doctors() {
  const [doctors, setDoctors] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/admin/doctors", {
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
      <h1>Doctors</h1>
      {doctors.map(d => (
        <p key={d.id}>{d.id}. {d.name}/dept: {d.specialization}</p>
      ))}
    </div>
  );
}

export default Doctors;
