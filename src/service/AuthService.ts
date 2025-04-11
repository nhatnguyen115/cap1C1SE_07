import { API_URIS } from "../api/URIConstant";
import {
  LoginResponse,
  LoginType,
  RegisterResponse,
  RegisterType,
} from "../types/auth";
import { httpNoAuth } from "./Http";

export const register = async (
  payload: RegisterType,
): Promise<RegisterResponse> => {
  try {
    const response = await httpNoAuth.post<RegisterResponse>(
      API_URIS.USER.ADD,
      payload,
    );
    return response.data; // Lấy phần body chứa { status, message }
  } catch (error) {
    console.error("Error during registration:", error);
    throw error;
  }
};

export const login = async (payload: LoginType): Promise<LoginResponse> => {
  try {
    const response = await httpNoAuth.post<LoginResponse>(
      API_URIS.AUTH.LOGIN,
      payload,
    );
    return response.data;
  } catch (error) {
    console.error("Error during login:", error);
    throw error;
  }
};
