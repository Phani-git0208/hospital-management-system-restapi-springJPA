import { useEffect, useState } from "react";

function App() {
  const [patients, setPatients] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/public/patient")
      .then(res => res.json())
      .then(data => {
        console.log(data);
        setPatients(data);
      })
      .catch(err => console.error("Error:", err));
  }, []);

  return (
    <div>
      <h1>Hospital Management System</h1>

      <h2>Patients List</h2>
      {patients.map(p => (
        <p key={p.id}>{p.name}</p>
      ))}
    </div>
  );
}

export default App;
