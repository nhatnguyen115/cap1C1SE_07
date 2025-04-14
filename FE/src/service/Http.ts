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
  const token = localStorage.getItem(LOCAL_STORAGE_CONSTANT.TOKEN);
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
