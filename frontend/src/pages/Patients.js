import { useEffect, useState } from "react";

function Patients() {
  const [patients, setPatients] = useState([]);

  useEffect(() => {
    fetch(`${process.env.REACT_APP_API_BASE_URL}/public/patient`)
      .then(res => res.json())
      .then(data => setPatients(data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div>
      <h1>Hospital Management System</h1>

      <h2>Patients List</h2>
      {patients.map(p => (
        <div key={p.id} style={{ border: "1px solid #ccc", margin: "10px", padding: "10px" }}>
          <h3>{p.name}</h3>
          <p>ID: {p.id}</p>
          <p>Age: {p.age}</p>
          <p>Email: {p.email}</p>
          <p>Gender: {p.gender}</p>
          <p>Phone: {p.phone}</p>
          <p>Blood Group: {p.bloodGroup}</p>

          <p>Insurance: {p.insurance ? JSON.stringify(p.insurance) : "None"}</p>

          <h4>Appointments:</h4>
          <ul>
            {p.appointments?.map(a => (
              <li key={a.id}>{JSON.stringify(a)}</li>
            ))}
          </ul>
        </div>
      ))}
    </div>
  );
}

export default Patients;
