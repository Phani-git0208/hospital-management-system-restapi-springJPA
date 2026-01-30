import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function BookAppointment() {
  const [doctors, setDoctors] = useState([]);
  const [doctorId, setDoctorId] = useState("");
  const [date, setDate] = useState("");
  const [time, setTime] = useState("");
  const [reason, setReason] = useState("");

  const token = localStorage.getItem("token");
  const navigate = useNavigate();

  // Load doctors
  useEffect(() => {
    fetch(`${process.env.REACT_APP_API_BASE_URL}/public/doctors`)
      .then(res => res.json())
      .then(data => setDoctors(data))
      .catch(err => console.error("Failed to load doctors", err));
  }, []);

  const handleSubmit = () => {
    if (!doctorId || !date || !time || !reason) {
      alert("Please fill all fields");
      return;
    }

    fetch(`${process.env.REACT_APP_API_BASE_URL}/patient/appointments`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      },
      body: JSON.stringify({
        doctorId: Number(doctorId),
        appointmentDate: date,
        appointmentTime: time,
        reason: reason
      })
    })
      .then(res => {
        if (!res.ok) throw new Error("Booking failed");
        return res.json();
      })
      .then(data => {
        alert("Appointment booked successfully!");
        console.log(data);

        // reset form
        setDoctorId("");
        setDate("");
        setTime("");
        setReason("");

  navigate("/dashboard"); // ✅ go back to dashboard
})
.catch(err => {
        console.error(err);
        alert("Failed to book appointment");
      });

  };

  return (
    <div style={{ padding: "20px" }}>
      <h2>Book Appointment</h2>

      <div>
        <label>Select Doctor:</label><br />
        <select value={doctorId} onChange={e => setDoctorId(e.target.value)}>
          <option value="">-- Select Doctor --</option>
          {doctors.map(doc => (
            <option key={doc.id} value={doc.id}>
              {doc.name} ({doc.specialization})
            </option>
          ))}
        </select>
      </div>

      <br />

      <div>
        <label>Date:</label><br />
        <input type="date" value={date} onChange={e => setDate(e.target.value)} />
      </div>

      <br />

      <div>
        <label>Time:</label><br />
        <input type="time" value={time} onChange={e => setTime(e.target.value)} />
      </div>

      <br />

      <div>
        <label>Reason:</label><br />
        <textarea
          value={reason}
          onChange={e => setReason(e.target.value)}
          placeholder="Describe your problem"
        />
      </div>

      <br />

      <button onClick={handleSubmit}>Book Appointment</button>
    </div>
  );
}

export default BookAppointment;
