import { useEffect, useState } from "react";

function Doctors() {
  const [doctors, setDoctors] = useState([]);

 useEffect(() => {
  const token = localStorage.getItem("token");
  console.log("TOKEN:", token);

  fetch("http://localhost:8080/admin/doctor", {
    headers: {
      Authorization: `Bearer ${token}`
    }
  })
    .then(res => {
      console.log("STATUS:", res.status);
      if (!res.ok) throw new Error("Unauthorized");
      return res.json();
    })
    .then(data => console.log("DATA:", data))
    .catch(err => console.error(err));
}, []);
}

export default Doctors;
