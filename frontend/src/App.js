import { Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Patients from "./pages/Patients";
import Doctors from "./pages/Doctors";
import MyProfile from "./pages/MyProfile";


function App() {
  return (
    <Routes>
      <Route path="/auth/login" element={<Login />} />
      <Route path="/auth/signup" element={<Signup />} />
      <Route path="/patients" element={<Patients />} />
      <Route path="/admin/doctors" element={<Doctors />} />
       <Route path="/me" element={<MyProfile />} />
    </Routes>
  );
}

export default App;
