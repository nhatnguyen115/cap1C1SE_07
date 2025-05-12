import React, { useEffect, useState } from "react";

import { useParams } from "react-router-dom";
import { API_URIS } from "../api/URIConstant";
import crown1 from "../assets/crown1.png";
import crown2 from "../assets/crown2.png";
import crown3 from "../assets/crown3.png";
import { http } from "../service/Http";
import { UserRank } from "../types/userTest";

const UserRankComponent = () => {
  const [rankings, setRankings] = useState<UserRank[]>([]);
  const { id } = useParams();
  useEffect(() => {
    http
      .get(API_URIS.USER_TEST.RANK, {
        params: {
          examId: id,
        },
      })
      .then((res) => {
        if (res.data.status === 200) {
          setRankings(res.data.data);
        }
      })
      .catch((err) => {
        console.error("Failed to fetch rankings", err);
      });
  }, [id]);

  const getCrown = (index: number) => {
    if (index === 0) return crown1;
    if (index === 1) return crown2;
    if (index === 2) return crown3;
    return null;
  };

  return (
    <div className="p-4 max-w-md mx-auto">
      <h2 className="text-xl font-bold text-center mb-4">Bảng xếp hạng</h2>

      <div className="flex justify-around items-end h-40 mb-6">
        {[1, 0, 2].map((i) => (
          <div
            key={i}
            className={`flex flex-col items-center ${
              i === 0 ? "h-40" : i === 1 ? "h-32" : "h-28"
            }`}
          >
            <img
              src={getCrown(i) || ""}
              alt="crown"
              className="w-10 h-10 mb-1"
            />
            <div className="w-16 h-16 rounded-full bg-orange-200 flex items-center justify-center font-bold">
              {rankings[i]?.username[0] || "?"}
            </div>
            <p className="text-sm mt-1">{rankings[i]?.username || "-"}</p>
            <p className="text-sm font-semibold">
              {rankings[i]?.totalScore ?? 0} điểm
            </p>
          </div>
        ))}
      </div>

      <div className="bg-white shadow-md rounded-lg overflow-hidden">
        {rankings.slice(3).map((user, index) => (
          <div
            key={index + 4}
            className="flex justify-between items-center p-3 border-b last:border-none"
          >
            <div className="flex items-center space-x-2">
              <div className="w-8 text-gray-500">{index + 4}</div>
              <div className="w-8 h-8 rounded-full bg-gray-300 text-center leading-8">
                {user.username[0]}
              </div>
              <span>{user.username}</span>
            </div>
            <span className="font-medium">{user.totalScore} điểm</span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default UserRankComponent;
