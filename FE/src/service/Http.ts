import axios from "axios";
import { LOCAL_STORAGE_CONSTANT } from "../constant/LocalStorageConstant";

const BASE_URL = import.meta.env.VITE_API_URL || "http://localhost:8080/";

// Instance có gắn token (mặc định)
const http = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// Gắn token vào request nếu có
http.interceptors.request.use((config) => {
  let token = localStorage.getItem(LOCAL_STORAGE_CONSTANT.TOKEN);

  // Nếu không có trong localStorage thì tìm trong cookie
  if (!token) {
    function getCookie(name: string) {
      const cookies = document.cookie.split(";");
      for (const cookie of cookies) {
        const [key, value] = cookie.trim().split("=");
        if (key === name) {
          return value;
        }
      }
      return null;
    }

    const tokenCookie = getCookie("token");
    token = tokenCookie;
  }
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// Instance không gắn token
const httpNoAuth = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export { http, httpNoAuth };
