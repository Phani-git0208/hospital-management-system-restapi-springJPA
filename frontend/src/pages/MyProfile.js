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
    fetch(`${process.env.REACT_APP_API_BASE_URL}/patient/me`, {
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
    fetch(`${process.env.REACT_APP_API_BASE_URL}/patient/EditProfile`, {
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

     <select value={gender} onChange={e => setGender(e.target.value)}>
  <option value="">Select Gender</option>
  <option value="MALE">Male</option>
  <option value="FEMALE">Female</option>
  <option value="OTHER">Other</option>
</select>
<br /><br />

      <select value={bloodGroup} onChange={e => setBloodGroup(e.target.value)}>
  <option value="">Select Blood Group</option>
  <option value="A_POS">A+</option>
  <option value="A_NEG">A-</option>
  <option value="B_POS">B+</option>
  <option value="B_NEG">B-</option>
  <option value="AB_POS">AB+</option>
  <option value="AB_NEG">AB-</option>
  <option value="O_POS">O+</option>
  <option value="O_NEG">O-</option>
</select>
<br /><br />

      <button onClick={handleSave}>Save Profile</button>

      <hr />

    </div>
  );
}

export default MyProfile;


