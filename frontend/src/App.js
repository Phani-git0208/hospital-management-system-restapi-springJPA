import { Routes, Route } from "react-router-dom";
import Login from "./pages/Login";
import Signup from "./pages/Signup";
import Dashboard from "./pages/Dashboard";
import MyProfile from "./pages/MyProfile";
import BookAppointment from "./pages/BookAppointment";
import MyAppointments from "./pages/MyAppointments";

function App() {
  return (
    <Routes>
      {/* Auth routes */}
      <Route path="/auth/login" element={<Login />} />
      <Route path="/auth/signup" element={<Signup />} />

      {/* Patient routes */}
      <Route path="/me" element={<MyProfile />} />
      <Route path="/myprofile" element={<MyProfile />} />
      <Route path="/dashboard" element={<Dashboard />} />

      <Route path="/book" element={<BookAppointment />} />
<Route path="/appointments" element={<MyAppointments />} />

    </Routes>
  );
}

export default App;

