export const API_URIS = {
  AUTH: {
    LOGIN: "/auth/introspect",
    REGISTER: "/auth/register",
    LOGOUT: "/auth/logout",
    REFRESH_TOKEN: "/auth/refresh-token",
    RESET_PASSWORD: "/auth/reset-password",
  },
  USER: {
    ADD: "/user",
    GET_PROFILE: "/user/profile",
    UPDATE_PROFILE: "/user/update",
    CHANGE_PASSWORD: "/user/change-password",
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
    GET_ALL: "/modules",
    GET_BY_ID: (id: number | string) => `/modules/${id}`,
    CREATE: "/modules",
    UPDATE: (id: number | string) => `/modules/${id}`,
    DELETE: (id: number | string) => `/modules/${id}`,
  },
  SECTION: {
    GET_ALL_BY_MODULE: (moduleId: number | string) =>
      `/sections?moduleId=${moduleId}`,
    GET_BY_ID: (id: number | string) => `/sections/${id}`,
  },
  PRACTICE: {
    GET_ALL_BY_SECTION: (sectionId: number | string) =>
      `/practice?sectionId=${sectionId}`,
    GET_BY_ID: (id: number | string) => `/practice/${id}`,
  },
  LESSON: {
    GET_BY_ID: (lessonId: number | string) => `/lessons/${lessonId}`,
  },
  MENU: {
    GET_ALL: "/menu",
    GET_BY_ID: (id: number | string) => `/menu/${id}`,
    CREATE: "/menu",
    UPDATE: (id: number | string) => `/menu/${id}`,
    DELETE: (id: number | string) => `/menu/${id}`,
  },
};
