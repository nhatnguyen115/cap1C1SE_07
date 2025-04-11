import { API_URIS } from "../api/URIConstant";
import http from "./Http";

export const getMenu = async () => {
  try {
    const response = await http.get(API_URIS.MENU.GET_ALL);
    return response.data;
  } catch (error) {
    console.error("Error fetching menu:", error);
    throw error;
  }
};
