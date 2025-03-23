import React from "react";
import { FaEye, FaHeart } from "react-icons/fa";

interface ResourceCardProps {
	imageSrc: string; // Đường dẫn ảnh
	title: string; // Tiêu đề
	views: number; // Lượt xem
	likes: number; // Lượt yêu thích
	onDetailClick?: () => void; // Sự kiện khi bấm nút "Xem chi tiết"
}

const ResourceCard: React.FC<ResourceCardProps> = ({
	imageSrc,
	title,
	views,
	likes,
	onDetailClick,
}) => {
	return (
		<div className="bg-white rounded-md shadow p-4 flex flex-col">
			<img
				src={imageSrc}
				alt="Resource"
				className="w-full h-40 object-cover rounded-md mb-4"
			/>
			<h3 className="text-gray-800 font-semibold mb-2">{title}</h3>
			<div className="flex items-center space-x-4 text-sm text-gray-500 mb-3">
				<div className="flex items-center space-x-1">
					<FaEye />
					<span>{views}+</span>
				</div>
				<div className="flex items-center space-x-1">
					<FaHeart />
					<span>{likes}</span>
				</div>
			</div>
			<button
				onClick={onDetailClick}
				className="mt-auto bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700 transition duration-300 text-sm"
			>
				Xem chi tiết
			</button>
		</div>
	);
};

export default ResourceCard;
