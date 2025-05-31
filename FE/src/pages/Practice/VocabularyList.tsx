import React, {useEffect, useState} from 'react';
import {http} from "../../service/Http";
import {useNavigate, useParams} from "react-router-dom";
import SearchComponent from "../../components/SearchComponent";

const VocabularyList = () => {
    const [words, setWords] = useState([]);
    const { sectionId } = useParams<{ sectionId: string }>();
    const navigate = useNavigate();

    useEffect(() => {
        const fetchWordList = async () => {
            try {
                const res = await http.get(`/vocabularies?sectionId=${sectionId}`);
                return res.data
            }catch (error) {
                console.log(error)
            }
        };
        fetchWordList().then(r => {
            setWords(r.data)
        })
    }, [sectionId]);

    const handleSearch = (word: string) => {
        if (!word.trim()) {
            alert('Vui lòng nhập từ cần tra!');
            return;
        }
        navigate(`/word/${word.trim()}`);
    };

    return (
        <div className="max-w-xl mx-auto mt-10 p-6 bg-white shadow-lg rounded-md">
            <SearchComponent/>
            <h2 className="text-2xl font-semibold mb-4">📘 Vocabulary List</h2>
            <table className="w-full table-auto border-collapse">
                <thead>
                <tr className="bg-gray-100">
                    <th className="text-left py-2 px-4 border-b font-medium">Word</th>
                    <th className="text-left py-2 px-4 border-b font-medium">Meaning</th>
                </tr>
                </thead>
                <tbody>
                {words.map((v, i) => (
                    <tr onClick={() => handleSearch(v.word)}
                        key={i} className="hover:bg-blue-50">
                        <td className="py-2 px-4 border-b">{v.word}</td>
                        <td className="py-2 px-4 border-b">{v.meaning}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default VocabularyList;