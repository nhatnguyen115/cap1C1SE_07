import { createRoot } from "react-dom/client";
import App from "./App.jsx";
import { UserProvider } from "./context/UserContext";
import "./index.css";

createRoot(document.getElementById("root")).render(
  //<StrictMode>
  <UserProvider>
    <App />,
  </UserProvider>,
  //</StrictMode>,
);
