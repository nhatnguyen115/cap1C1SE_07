// eslint-disable-next-line no-unused-vars
import React, { useState } from "react";
import Header from "./common/Header";
import Footer from "./common/Footer";
import Chatbot from "../pages/Chatbot/Chatbot";

// eslint-disable-next-line react/prop-types
const MainLayout = ({ children }) => {
	const [isopen, setIsOpen] = useState(false);
	return (
		<>
			<Header />
			<main className="min-h-screen p-4">{children}</main>
			<Chatbot isOpen={isopen} setIsOpen={setIsOpen} />
			<Footer />
		</>
	);
};

export default MainLayout;
