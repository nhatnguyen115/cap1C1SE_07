export const API_URIS = {
  AUTH: {
    LOGIN: "/auth/login",
    REGISTER: "/auth/register",
    LOGOUT: "/auth/logout",
    REFRESH_TOKEN: "/auth/refresh-token",
  },
  USER: {
    GET_PROFILE: "/users/profile",
    UPDATE_PROFILE: "/users/update",
    CHANGE_PASSWORD: "/users/change-password",
  },
  PRODUCT: {
    GET_ALL: "/products",
    GET_BY_ID: (id: number | string) => `/products/${id}`,
    CREATE: "/products",
    UPDATE: (id: number | string) => `/products/${id}`,
    DELETE: (id: number | string) => `/products/${id}`,
  },
  ORDER: {
    CREATE: "/orders",
    GET_ALL: "/orders",
    GET_BY_ID: (id: number | string) => `/orders/${id}`,
  },
  MODULE: {
    GET_ALL: "/module",
    GET_BY_ID: (id: number | string) => `/module/${id}`,
    CREATE: "/module",
    UPDATE: (id: number | string) => `/module/${id}`,
    DELETE: (id: number | string) => `/module/${id}`,
  },
  MENU: {
    GET_ALL: "/menu",
    GET_BY_ID: (id: number | string) => `/menu/${id}`,
    CREATE: "/menu",
    UPDATE: (id: number | string) => `/menu/${id}`,
    DELETE: (id: number | string) => `/menu/${id}`,
  },
};
