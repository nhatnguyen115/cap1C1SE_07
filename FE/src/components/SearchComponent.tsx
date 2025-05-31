import React, {useState} from "react"
import {useNavigate} from "react-router-dom";

const SearchComponent: React.FC = () => {
    const [word, setWord] = useState('');
    const navigate = useNavigate();

    const handleSearch = () => {
        if (!word.trim()) {
            alert('Vui lòng nhập từ cần tra!');
            return;
        }
        navigate(`/word/${word.trim()}`);
    };
    return (
        <div className="w-full max-w-4xl mx-auto mt-2 px-4 mb-8">
            <div
                className="w-full p-4 bg-white rounded-xl shadow-md border border-gray-200 flex items-center space-x-4">
                <div className="text-blue-600 text-xl">
                    🔍
                </div>

                <div className="text-blue-600 font-semibold text-lg whitespace-nowrap">
                    Tra từ
                </div>

                <div className="flex-grow flex items-center space-x-2">
                    <input
                        type="text"
                        placeholder="Nhập từ bạn muốn tra..."
                        value={word}
                        onChange={(e) => setWord(e.target.value)}
                        className="w-full px-4 py-2 text-sm border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:outline-none transition"
                    />
                    <button
                        onClick={handleSearch}
                        className="px-4 py-2 text-sm bg-blue-600 text-white rounded-md hover:bg-blue-700 transition">
                        Tìm
                    </button>
                </div>
            </div>
        </div>
    )
}
export default SearchComponent