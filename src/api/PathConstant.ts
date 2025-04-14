export const PATH_CONSTANTS = {
  ROOT: {
    ROOT: "/",
  },
  AUTH: {
    LOGIN: "/login",
    REGISTER: "/register",
    LOGOUT: "/auth/logout",
    REFRESH_TOKEN: "/auth/refresh-token",
    RESET_PASSWORD: "/forgot-password",
  },
  USER: {
    ADD: "/user/add",
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
    GET_BY_ID: (sectionId: number) => `/sections/${sectionId}`,
  },
  PRACTICE: {
    GET_ALL_BY_SECTION: (sectionId: number | string) =>
      `/practice?sectionId=${sectionId}`,
    GET_BY_ID: (id: number | string) => `/practice/${id}`,
  },
  LESSON: {
    GET_BY_ID: (lessonId: number | string) => `/lesson/${lessonId}`,
  },
  MENU: {
    GET_ALL: "/menu",
    GET_BY_ID: (id: number | string) => `/menu/${id}`,
    CREATE: "/menu",
    UPDATE: (id: number | string) => `/menu/${id}`,
    DELETE: (id: number | string) => `/menu/${id}`,
  },
  ADMIN: {
    ADMIN_DASHBOARD: "/admin/dashboard",
    ADMIN_USERMANAGEMENT: "/admin/usermanagement",
  },
  MOCK_TEST: {
    MOCK_TEST: "/mock-test",
  },

  PAYMENT: {
    FORM: "/payment/paymentform",
  },

  SETTING: {
    SETTING: "/settings",
    ADD: "/settings/editsettings",
  },
};
