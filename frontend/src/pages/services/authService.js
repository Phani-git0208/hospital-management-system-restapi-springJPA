import axios from "axios";

const API_URL = `${process.env.REACT_APP_API_BASE_URL}/auth/login`;

export const loginUser = async (username, password) => {
  const response = await axios.post(API_URL, {
    username,
    password,
  });

  return response.data;
};
