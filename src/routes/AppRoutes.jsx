import { createBrowserRouter, RouterProvider } from "react-router-dom";

// Các trang (các phần này sẽ thêm sau)
import HomePage from "../pages/Home/Home";
import Login from "../pages/Login/Login";
import NotFound from "../pages/NotFound/NotFound";
import ProtectedRoute from "./ProtectedRoute";

// Layout
import MainLayout from "../layouts/MainLayout";
import Register from "../pages/Register/Register";
import { MockTest } from "../pages/MockTest/MockTest";
import { Test } from "../pages/MockTest/Test";
import ForgotPassword from "../pages/ForgotPassword/ForgotPassword";
import ResetPassword from "../pages/ForgotPassword/ResetPassword";
import Leaderboard from "../pages/Leaderboard/Leaderboard";
import Result from "../pages/MockTest/Result";
 // Giả lập trạng thái Auth
const isAuthenticated = false; // Kiểm tra trạng thái đăng nhập

// Cấu hình routes
const routes = [
  {
    path: "/", // Trang Home, cho mọi user
    element: (
      <MainLayout>
        <HomePage />
      </MainLayout>
    ),
  },
  {
    path: "/login", // Trang dành cho Guest (Guest-only)
    element: (
      <ProtectedRoute isAuthenticated={isAuthenticated} guestOnly={true}>
        <MainLayout>
          <Login />
        </MainLayout>
      </ProtectedRoute>
    ),
  },
  {
    path: "/forgot-password", // Trang dành cho Guest (Guest-only)
    element: (
      <ProtectedRoute isAuthenticated={isAuthenticated} guestOnly={true}>
        <MainLayout>
          <ForgotPassword />
        </MainLayout>
      </ProtectedRoute>
    ),
  },
  {
    path: "*", // Trang 404
    element: (
      <MainLayout>
        <NotFound />
      </MainLayout>
    ),
  },
  {
    path: "/reset-password", // Trang 404
    element: (
      <MainLayout>
        <ResetPassword />
      </MainLayout>
    ),
  },

  {
    path: "/register",
    element: (
      <MainLayout>
        <Register />
      </MainLayout>
    ),
  },

  {
    path: "/mock-test",
    element: (
      <MainLayout>
        <MockTest />
      </MainLayout>
    ),
  },
  {
    path: "/mock-test/:id",
    element: (
      <MainLayout>
        <Test />
      </MainLayout>
    ),
  },
  {
    path: "/leaderboard",
    element: (
      <MainLayout>
        <Leaderboard />
      </MainLayout>
    ),
  },
  {
    path: "/result/:id",
    element: (
      <MainLayout>
        <Result totalQuestions={100} correctAnswers={90} wrongAnswers={10} skippedQuestions={0} score={90}/>
      </MainLayout>
    ),
  },

];

const AppRoutes = () => {
  const router = createBrowserRouter(routes);
  return <RouterProvider router={router} />;
};

export default AppRoutes;
