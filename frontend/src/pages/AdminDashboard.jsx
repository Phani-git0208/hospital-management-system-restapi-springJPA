import { useNavigate } from "react-router-dom";

function AdminDashboard() {
  const navigate = useNavigate();

  return (
    <div style={{ padding: "40px" }}>
      <h2>Admin Dashboard</h2>

      <div style={{ marginTop: "20px" }}>
        <button onClick={() => navigate("/admin/doctors")}>
          View Doctors
        </button>
      </div>

      <div style={{ marginTop: "20px" }}>
        <button onClick={() => navigate("/patients")}>
          View Patients
        </button>
      </div>
    </div>
  );
}

export default AdminDashboard;

