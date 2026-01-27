import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function MyProfile() {
  const [profile, setProfile] = useState(null);
  const [loading, setLoading] = useState(true);

  const [age, setAge] = useState("");
  const [phone, setPhone] = useState("");
  const [gender, setGender] = useState("");
  const [bloodGroup, setBloodGroup] = useState("");

  const token = localStorage.getItem("token");
  const navigate = useNavigate();

  // Load profile
  useEffect(() => {
    fetch("http://localhost:8080/patient/me", {
      headers: {
        Authorization: `Bearer ${token}`
      }
    })
      .then(async res => {
        if (!res.ok) throw new Error("Failed to fetch");
        return res.json();
      })
      .then(data => {
        setProfile(data);

        setAge(data.age || "");
        setPhone(data.phone || "");
        setGender(data.gender || "");
        setBloodGroup(data.bloodGroup || "");

        // ✅ If profile already complete → redirect to dashboard
        if (data.age && data.phone && data.gender && data.bloodGroup) {
          navigate("/dashboard");
        }

        setLoading(false);
      })
      .catch(err => {
        console.error(err);
        alert("Failed to load profile");
        setLoading(false);
      });
  }, [navigate, token]);

  // Save updated profile
  const handleSave = () => {
    fetch("http://localhost:8080/patient/me", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`
      },
      body: JSON.stringify({
        age,
        phone,
        gender,
        bloodGroup
      })
    })
      .then(async res => {
        if (!res.ok) throw new Error("Update failed");
        return res.json();
      })
      .then(updated => {
        alert("Profile updated successfully!");
        setProfile(updated);

        // ✅ After save → go to dashboard
        navigate("/dashboard");
      })
      .catch(err => {
        console.error(err);
        alert("Update failed");
      });
  };

  if (loading) return <p>Loading...</p>;
  if (!profile) return <p>No profile found</p>;

  return (
    <div style={{ padding: "20px" }}>
      <h2>My Profile</h2>

      {/* Basic info */}
      <p><b>Name:</b> {profile.name}</p>
      <p><b>Email:</b> {profile.email}</p>

      <hr />

      <h3>Complete / Edit Profile</h3>

      <input
        placeholder="Age"
        value={age}
        onChange={e => setAge(e.target.value)}
      /><br /><br />

      <input
        placeholder="Phone"
        value={phone}
        onChange={e => setPhone(e.target.value)}
      /><br /><br />

      <input
        placeholder="Gender"
        value={gender}
        onChange={e => setGender(e.target.value)}
      /><br /><br />

      <input
        placeholder="Blood Group (e.g. O_POSITIVE)"
        value={bloodGroup}
        onChange={e => setBloodGroup(e.target.value)}
      /><br /><br />

      <button onClick={handleSave}>Save Profile</button>

      <hr />

      <h3>Appointments</h3>
      {profile.appointments && profile.appointments.length > 0 ? (
        profile.appointments.map(a => (
          <div key={a.id} style={{ border: "1px solid gray", margin: "10px", padding: "10px" }}>
            <p>Doctor: {a.doctorName}</p>
            <p>Date: {a.date}</p>
            <p>Status: {a.status}</p>
          </div>
        ))
      ) : (
        <p>No appointments yet</p>
      )}
    </div>
  );
}

export default MyProfile;


