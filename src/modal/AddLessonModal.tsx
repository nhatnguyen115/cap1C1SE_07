import React, { useState } from "react";

interface AddLessonModalProps {
  isOpen: boolean;
  onClose: () => void;
  onAddLesson: (
    lessonName: string,
    contentType: string,
    articleText: string,
    duration: number,
  ) => void;
}

const AddLessonModal: React.FC<AddLessonModalProps> = ({
  isOpen,
  onClose,
  onAddLesson,
}) => {
  const [lessonName, setLessonName] = useState("");
  const [contentType, setContentType] = useState("VIDEO");
  const [articleText, setArticleText] = useState("");
  const [hours, setHours] = useState(0);
  const [minutes, setMinutes] = useState(0);

  if (!isOpen) return null;

  const handleSubmit = () => {
    if (!lessonName.trim()) {
      alert("Vui lòng nhập tên bài học.");
      return;
    }

    const totalDuration = hours * 60 + minutes;
    onAddLesson(lessonName.trim(), contentType, articleText, totalDuration);
    onClose();
  };

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded shadow-lg w-96">
        <h2 className="text-lg font-semibold mb-4">Thêm bài học</h2>

        {/* Tên bài học */}
        <div className="mb-4">
          <label className="block mb-1 text-sm">Tên bài học</label>
          <input
            type="text"
            value={lessonName}
            onChange={(e) => setLessonName(e.target.value)}
            className="w-full border border-gray-300 rounded px-2 py-1 text-sm"
            placeholder="Nhập tên bài học"
          />
        </div>

        {/* Loại nội dung */}
        <div className="mb-4">
          <label className="block mb-1 text-sm">Loại nội dung</label>
          <select
            value={contentType}
            onChange={(e) => setContentType(e.target.value)}
            className="w-full border border-gray-300 rounded px-2 py-1 text-sm"
          >
            <option value="VIDEO">VIDEO</option>
            <option value="TEXT">TEXT</option>
          </select>
        </div>

        {contentType === "TEXT" && (
          <div className="mb-4">
            <label className="block mb-1 text-sm">
              Nội dung bài học (TEXT)
            </label>
            <textarea
              value={articleText}
              onChange={(e) => setArticleText(e.target.value)}
              className="w-full border border-gray-300 rounded px-2 py-1 text-sm"
              rows={4}
              placeholder="Nhập nội dung bài học"
            ></textarea>
          </div>
        )}

        {/* Thời lượng */}
        <div className="mb-4">
          <label className="block mb-1 text-sm">Thời lượng</label>
          <div className="flex gap-2">
            <div className="w-1/2">
              <label className="block text-xs text-gray-500 mb-1">Giờ</label>
              <input
                type="text"
                inputMode="numeric"
                pattern="[0-9]*"
                value={hours === 0 ? "" : hours}
                onChange={(e) => {
                  const val = e.target.value;
                  if (/^\d*$/.test(val)) {
                    setHours(val === "" ? 0 : parseInt(val, 10));
                  }
                }}
                className="w-full border border-gray-300 rounded px-2 py-1 text-sm"
                placeholder="0"
              />
            </div>
            <div className="w-1/2">
              <label className="block text-xs text-gray-500 mb-1">Phút</label>
              <input
                type="text"
                inputMode="numeric"
                pattern="[0-9]*"
                value={minutes === 0 ? "" : minutes}
                onChange={(e) => {
                  const val = e.target.value;
                  if (/^\d*$/.test(val)) {
                    setMinutes(val === "" ? 0 : parseInt(val, 10));
                  }
                }}
                className="w-full border border-gray-300 rounded px-2 py-1 text-sm"
                placeholder="0"
              />
            </div>
          </div>
        </div>

        {/* Button */}
        <div className="flex justify-end gap-2">
          <button
            onClick={onClose}
            className="px-3 py-1 bg-gray-300 rounded text-sm"
          >
            Huỷ
          </button>
          <button
            onClick={handleSubmit}
            className="px-3 py-1 bg-blue-600 text-white rounded text-sm"
          >
            Thêm
          </button>
        </div>
      </div>
    </div>
  );
};

export default AddLessonModal;
