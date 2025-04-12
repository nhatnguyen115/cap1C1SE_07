import React, { useEffect, useState } from "react";
import LeftSidebarAdmin from "../../../components/LeftSidebarAdmin";
import { FaEllipsisH, FaTimes, FaUpload } from "react-icons/fa";
import { http } from "../../../service/Http";
import { SectionFormData } from "../../../types/section";
import { ModuleType } from "../../../types/module";
import { SectionTypeData } from "../../../data/sectionTypeData";
import { ResponseDataType } from "../../../types/response";

const SectionManagementPage: React.FC = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const [modules, setModules] = useState<ModuleType[]>([]);
  const [formData, setFormData] = useState<SectionFormData>({
    sectionName: "",
    description: "",
    sectionType: "",
    moduleId: "",
  });

  const [errors, setErrors] = useState({
    sectionName: false,
    sectionType: false,
    moduleId: false,
  });

  const [message, setMessage] = useState<string>("");
  const [isError, setIsError] = useState<boolean>(false);

  useEffect(() => {
    const fetchModules = async () => {
      try {
        const res = await http.get("/modules");
        setModules(res.data.data);
      } catch (err) {
        console.error("Lỗi khi lấy module:", err);
      }
    };

    fetchModules();
  }, []);

  const handleChange = (
    e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>,
  ) => {
    const { name, value } = e.target;
    setFormData((prev: any) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = async () => {
    const newErrors = {
      sectionName: !formData.sectionName.trim(),
      sectionType: !formData.sectionType,
      moduleId: !formData.moduleId,
    };

    setErrors(newErrors);

    const hasError = Object.values(newErrors).some((v) => v);
    if (hasError) return;

    const { moduleId, ...sectionPayload } = formData;

    try {
      const res = await http.post<ResponseDataType<any>>(
        `/modules/${moduleId}/sections`,
        sectionPayload,
      );

      const response = res.data;

      if (response.status === 500) {
        setMessage(response.message || "Section could not be added");
        setIsError(true);

        setTimeout(() => {
          setMessage("");
          setIsError(false);
        }, 3000);
        return;
      }

      // Nếu thành công
      setIsModalOpen(false);
      setFormData({
        sectionName: "",
        description: "",
        sectionType: "",
        moduleId: "",
      });
      setErrors({
        sectionName: false,
        sectionType: false,
        moduleId: false,
      });
    } catch (err) {
      console.error("Lỗi khi thêm section:", err);
      setMessage("Thêm section thất bại.");
      setIsError(true);

      setTimeout(() => {
        setMessage("");
        setIsError(false);
      }, 3000);
    }
  };

  const Sectionsdata = [
    {
      id: "BT1",
      name: "TOEIC Full Section 1",
      type: "FULL Section",
      questions: 100,
      time: "2h",
      date: "20/03/2025",
    },
    {
      id: "BT2",
      name: "TOEIC Full Section 2",
      type: "LISTENING",
      questions: 100,
      time: "2h",
      date: "21/03/2025",
    },
    {
      id: "BT3",
      name: "TOEIC Full Section 3",
      type: "READING",
      questions: 100,
      time: "2h",
      date: "22/03/2025",
    },
    {
      id: "BT4",
      name: "TOEIC Full Section 4",
      type: "FULL Section",
      questions: 100,
      time: "2h",
      date: "23/03/2025",
    },
    {
      id: "BT5",
      name: "TOEIC Listening Practice 1",
      type: "LISTENING",
      questions: 50,
      time: "1h",
      date: "24/03/2025",
    },
    {
      id: "BT6",
      name: "TOEIC Reading Practice 1",
      type: "READING",
      questions: 50,
      time: "1h",
      date: "25/03/2025",
    },
    {
      id: "BT7",
      name: "TOEIC Full Section 5",
      type: "FULL Section",
      questions: 100,
      time: "2h",
      date: "26/03/2025",
    },
    {
      id: "BT8",
      name: "TOEIC Listening Practice 2",
      type: "LISTENING",
      questions: 50,
      time: "1h",
      date: "27/03/2025",
    },
    {
      id: "BT9",
      name: "TOEIC Reading Practice 2",
      type: "READING",
      questions: 50,
      time: "1h",
      date: "28/03/2025",
    },
    {
      id: "BT10",
      name: "TOEIC Full Section 6",
      type: "FULL Section",
      questions: 100,
      time: "2h",
      date: "29/03/2025",
    },
  ];

  return (
    <div className="min-h-screen flex bg-gray-100">
      <LeftSidebarAdmin customHeight="h-auto w-64" />
      <div className="flex-1 p-8">
        <div className="flex justify-between items-center mb-6">
          <h1 className="text-3xl font-bold text-gray-800">Quản lý section</h1>
          <button
            className="bg-blue-600 text-white px-6 py-3 rounded-md hover:bg-blue-700 transition"
            onClick={() => setIsModalOpen(true)}
          >
            Thêm section mới
          </button>
        </div>

        {/* Bảng danh sách section */}
        <div className="bg-white shadow-lg rounded-xl overflow-hidden">
          <table className="w-full table-auto">
            <thead>
              <tr className="bg-gray-200 text-gray-700 uppercase text-sm leading-normal">
                <th className="py-3 px-4 text-left">ID</th>
                <th className="py-3 px-4 text-left">Tên bài thi</th>
                <th className="py-3 px-4 text-left">Loại bài thi</th>
                <th className="py-3 px-4 text-center">Số câu hỏi</th>
                <th className="py-3 px-4 text-center">Thời gian</th>
                <th className="py-3 px-4 text-center">Ngày tạo</th>
                <th className="py-3 px-4 text-center">Hành động</th>
              </tr>
            </thead>
            <tbody className="text-gray-600 text-sm">
              {Sectionsdata.map((exam, index) => (
                <tr
                  key={exam.id}
                  className={`border-b hover:bg-gray-100 transition ${
                    index % 2 === 0 ? "bg-gray-50" : ""
                  }`}
                >
                  <td className="py-4 px-4">{exam.id}</td>
                  <td className="py-4 px-4">{exam.name}</td>
                  <td className="py-4 px-4">
                    <span
                      className={`px-2 py-1 text-xs rounded-full font-semibold ${
                        exam.type === "FULL Section"
                          ? "bg-blue-200 text-blue-800"
                          : exam.type === "LISTENING"
                          ? "bg-orange-200 text-orange-800"
                          : "bg-yellow-200 text-yellow-800"
                      }`}
                    >
                      {exam.type}
                    </span>
                  </td>
                  <td className="py-4 px-4 text-center">{exam.questions}</td>
                  <td className="py-4 px-4 text-center">{exam.time}</td>
                  <td className="py-4 px-4 text-center">{exam.date}</td>
                  <td className="py-4 px-4 text-center">
                    <button className="text-gray-500 hover:text-gray-700 transition">
                      <FaEllipsisH size={18} />
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>

      {/* Modal Thêm Câu Hỏi */}
      {isModalOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-50">
          <div className="bg-white p-6 rounded-lg shadow-lg w-96 relative">
            <button
              className="absolute top-3 right-3 text-red-600 text-xl"
              onClick={() => setIsModalOpen(false)}
            >
              <FaTimes />
            </button>

            <h2 className="text-lg font-semibold mb-4">Thêm section</h2>
            {message && (
              <div
                className={`fixed top-5 z-50 bg-${
                  isError ? "red" : "green"
                }-500 text-red-700 px-6 py-3 rounded shadow-lg animate-slideDown z-50`}
              >
                {message}
              </div>
            )}
            <div className="space-y-3">
              <div>
                <label className="block text-sm font-medium">
                  Tên section <span className="text-red-500">*</span>
                </label>
                <input
                  type="text"
                  name="sectionName"
                  value={formData.sectionName}
                  onChange={handleChange}
                  className={`w-full border px-3 py-2 rounded-md ${
                    errors.sectionName ? "border-red-500" : "border-gray-300"
                  }`}
                  placeholder="Nhập tên section"
                />
              </div>

              <div>
                <label className="block text-sm font-medium">Mô tả</label>
                <input
                  type="text"
                  name="description"
                  value={formData.description}
                  onChange={handleChange}
                  className="w-full border px-3 py-2 rounded-md"
                  placeholder="Nhập mô tả"
                />
              </div>

              <div>
                <label className="block text-sm font-medium">
                  Loại section <span className="text-red-500">*</span>
                </label>
                <select
                  name="sectionType"
                  value={formData.sectionType}
                  onChange={handleChange}
                  className={`w-full border px-3 py-2 rounded-md ${
                    errors.sectionType ? "border-red-500" : "border-gray-300"
                  }`}
                >
                  <option value="">Chọn loại section</option>
                  {SectionTypeData.map((type) => (
                    <option key={type} value={type}>
                      {type}
                    </option>
                  ))}
                </select>
              </div>

              <div>
                <label className="block text-sm font-medium">
                  Module <span className="text-red-500">*</span>
                </label>
                <select
                  name="moduleId"
                  value={formData.moduleId}
                  onChange={handleChange}
                  className={`w-full border px-3 py-2 rounded-md ${
                    errors.moduleId ? "border-red-500" : "border-gray-300"
                  }`}
                >
                  <option value="">Chọn module</option>
                  {modules.map((mod) => (
                    <option key={mod.id} value={mod.id}>
                      {mod.moduleName}
                    </option>
                  ))}
                </select>
              </div>
            </div>

            <button
              onClick={handleSubmit}
              className="mt-4 w-full py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 transition"
            >
              Thêm Section
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default SectionManagementPage;
