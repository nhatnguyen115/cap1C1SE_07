import React from 'react';
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

function WordDetail() {
    const { word } = useParams();
    const [data, setData] = useState(null);
    const [error, setError] = useState('');

    useEffect(() => {
        const fetchDefinition = async () => {
            try {
                setError('');
                const res = await fetch(`https://api.dictionaryapi.dev/api/v2/entries/en/${word}`);
                if (!res.ok) throw new Error('Không tìm thấy từ này!');
                const json = await res.json();
                setData(json[0]);
            } catch (err) {
                setError(err.message);
                setData(null);
            }
        };

        fetchDefinition();
    }, [word]);

    return (
        <div className="p-6 max-w-3xl mx-auto">
            <h2 className="text-2xl font-bold mb-4">Kết quả cho: <span className="text-blue-600">{word}</span></h2>

            {error && <p className="text-red-600">{error}</p>}

            {data && (
                <div className="space-y-4">
                    <p><strong>Phát âm:</strong> {data.phonetics?.[0]?.text || 'Không có'}</p>

                    {data.meanings.map((meaning, idx) => (
                        <div key={idx} className="p-4 bg-gray-100 rounded-md">
                            <p><strong>Loại từ:</strong> {meaning.partOfSpeech}</p>
                            <ul className="list-disc pl-5 mt-2">
                                {meaning.definitions.map((def, i) => (
                                    <li key={i}>
                                        <p>{def.definition}</p>
                                        {def.example && <p className="text-sm text-gray-600">Ví dụ: {def.example}</p>}
                                    </li>
                                ))}
                            </ul>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}
export default WordDetail;
