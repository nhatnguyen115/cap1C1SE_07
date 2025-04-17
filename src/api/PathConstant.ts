export const PATH_CONSTANTS = {
  ROOT: {
    ROOT: "/",
  },

  AUTH: {
    LOGIN: "/login",
    REGISTER: "/register",
    LOGOUT: "/logout",
    REFRESH_TOKEN: "/auth/refresh-token",
    RESET_PASSWORD: "/reset-password",
    FORGOT_PASSWORD: "/forgot-password",
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
    GET_BY_ID: (sectionId: number | string) => `/sections/${sectionId}`,
    LIST: "/sections",
    DETAIL: (sectionId: string | number) => `/sections/${sectionId}`,
    MANAGEMENT: "/admin/sectionmanagement",
    DETAIL_MANAGEMENT: "/section-detail",
  },

  PRACTICE: {
    ROOT: "/practice",
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
    TEST_MANAGEMENT: "/admin/testmanagement",
    SECTION_MANAGEMENT: "/admin/sectionmanagement",
  },

  MOCK_TEST: {
    MOCK_TEST: "/mock-test",
    MOCK_TEST_BY_ID: (id: string | number) => `/mock-test/${id}`,
    MOCK_TEST_VIEW_BY_ID: (id: string | number) => `/mock-test/view/${id}`,
  },

  PAYMENT: {
    ROOT: "/payment",
    FORM: "/payment/paymentform",
  },

  SETTING: {
    SETTING: "/settings",
    ADD: "/settings/editsettings",
  },

  HISTORY: {
    HISTORY: "/history",
  },

  RESULT: {
    BY_ID: (id: number | string) => `/result/${id}`,
  },

  LEADERBOARD: "/leaderboard",

  RESOURCE: {
    ROOT: "/resource",
    DETAIL: (id: number | string) => `/resource/${id}`,
  },

  EXAM: {
    TEST: "/test",
    EXAMS_VIEW_BY_ID: "/exams/view/:id",
    EXAMS: "/exams",
  },
  PART: {
    PARTS: "/parts",
    DETAIL: (partId: number | string) => `/parts/${partId}`,
    DETAIL_PATH: "/parts/:partId", // dùng cho Route
  },
};
