import React, { useEffect, useState } from "react";
import { FaBan, FaCheckCircle, FaPencilAlt } from "react-icons/fa";
import { API_URIS } from "../../../api/URIConstant";
import LeftSidebarAdmin from "../../../components/LeftSidebarAdmin";
import PaginationComponent from "../../../components/PaginationComponent";
import { PAGINATION_CONSTANT } from "../../../constant/PaginationConstant";
import { http } from "../../../service/Http";

import ConfirmDialogComponent from "../../../components/ConfirmDialogComponent";
import EditUserModalComponent from "../../../modal/EditUserModalComponent";
import { UserInfoType } from "../../../types/user";

const UserManagementPage: React.FC = () => {
  const [userList, setUserList] = useState<UserInfoType[]>([]);

  const [currentPage, setCurrentPage] = useState(PAGINATION_CONSTANT.PAGE[0]);
  const [pageSize] = useState(PAGINATION_CONSTANT.SIZE[10]);
  const [totalPages, setTotalPages] = useState(0);

  const [selectedUser, setSelectedUser] = useState<UserInfoType | null>(null);
  const [isEditModalOpen, setIsEditModalOpen] = useState(false);
  const [isConfirmOpen, setIsConfirmOpen] = useState(false);

  // Hàm mở modal sửa
  const openEditModal = (user: UserInfoType) => {
    setSelectedUser(user);
    setIsEditModalOpen(true);
  };

  // Hàm mở modal xác nhận disable
  const openConfirmDialog = (user: UserInfoType) => {
    setSelectedUser(user);
    setIsConfirmOpen(true);
  };

  const handleDisableUser = async () => {
    if (!selectedUser) return;

    try {
      const isDisabling = selectedUser.active === true;

      const apiCall = isDisabling
        ? http.put(API_URIS.USER.DISABLE(selectedUser.id))
        : http.put(API_URIS.USER.ENABLE(selectedUser.id));

      await apiCall;

      setUserList((prev) =>
        prev.map((u) =>
          u.id === selectedUser.id ? { ...u, active: !isDisabling } : u,
        ),
      );
    } catch (error) {
      console.error("Lỗi khi cập nhật trạng thái người dùng:", error);
    } finally {
      setIsConfirmOpen(false);
    }
  };

  const handleUpdateUser = async (updatedUser: UserInfoType) => {
    try {
      await http.put(API_URIS.USER.ADD, updatedUser);
      setUserList((prev) =>
        prev.map((u) =>
          u.id === updatedUser.id ? { ...u, ...updatedUser } : u,
        ),
      );
    } catch (error) {
      console.error("Lỗi khi cập nhật người dùng:", error);
    }
  };

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await http.get(API_URIS.USER.PAGING, {
          params: {
            page: currentPage,
            size: pageSize,
          },
        });
        setUserList(response.data.data.content || []);
        setTotalPages(response.data.data.totalPages);
      } catch (error) {
        console.error("Lỗi khi lấy danh sách người dùng:", error);
      }
    };

    fetchUsers();
  }, [currentPage]);

  return (
    <div className="min-h-screen flex flex-row">
      {/* Sidebar Admin */}
      <LeftSidebarAdmin customHeight="h-auto w-64" />

      {/* Main Content */}
      <div className="flex-1 p-8 bg-gray-100">
        <h1 className="text-3xl font-bold text-gray-800 mb-6">
          Quản lý người dùng
        </h1>

        {/* Bảng người dùng */}
        <div className="overflow-x-auto bg-white rounded-lg shadow-md">
          <table className="w-full table-auto">
            <thead className="bg-blue-600 text-white">
              <tr>
                <th className="px-6 py-3 text-left">Username</th>

                <th className="px-6 py-3 text-left">Tên</th>
                <th className="px-6 py-3 text-left">Email</th>
                <th className="px-6 py-3 text-left">Số điện thoại</th>
                <th className="px-6 py-3 text-left">Ngày đăng ký</th>
                <th className="px-6 py-3 text-left">Trạng thái</th>
                <th className="px-6 py-3 text-center">Tùy chọn</th>
              </tr>
            </thead>
            <tbody>
              {userList.map((user) => (
                <tr key={user.id} className="border-t">
                  <td className="px-6 py-3">{user.username}</td>
                  <td className="px-6 py-3">
                    {user.firstName} {user.lastName}
                  </td>
                  <td className="px-6 py-3">{user.email}</td>
                  <td className="px-6 py-3">{user.phoneNumber}</td>
                  <td className="px-6 py-3">
                    {new Date(user.createdAt).toLocaleDateString()}
                  </td>
                  <td className="px-6 py-3">
                    <span
                      className={`inline-block px-3 py-1 rounded-full text-white ${
                        user.active ? "bg-green-500" : "bg-gray-400"
                      }`}
                    >
                      {user.active ? "Hoạt động" : "Không hoạt động"}
                    </span>
                  </td>
                  <td className="px-6 py-3 text-center flex justify-center gap-3">
                    <button
                      onClick={() => openEditModal(user)}
                      className="text-blue-600 hover:text-blue-800"
                    >
                      <FaPencilAlt />
                    </button>
                    {user.active ? (
                      <button
                        onClick={() => openConfirmDialog(user)}
                        className="text-red-600 hover:text-red-800"
                      >
                        <FaBan />
                      </button>
                    ) : (
                      <button
                        onClick={() => openConfirmDialog(user)}
                        className="text-green-600 hover:text-green-800"
                      >
                        <FaCheckCircle />
                      </button>
                    )}
                  </td>
                </tr>
              ))}
              {userList.length === 0 && (
                <tr>
                  <td colSpan={7} className="text-center py-6 text-gray-500">
                    Không có dữ liệu người dùng.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
        <EditUserModalComponent
          isOpen={isEditModalOpen}
          onClose={() => setIsEditModalOpen(false)}
          onSubmit={handleUpdateUser}
          user={selectedUser}
        />

        <ConfirmDialogComponent
          isOpen={isConfirmOpen}
          title="Xác nhận vô hiệu hóa"
          message={`Bạn có chắc chắn muốn vô hiệu hóa người dùng ${selectedUser?.username}?`}
          onConfirm={handleDisableUser}
          onCancel={() => setIsConfirmOpen(false)}
        />

        <PaginationComponent
          currentPage={currentPage}
          totalPages={totalPages}
          onPageChange={(page) => setCurrentPage(page)}
        />
      </div>
    </div>
  );
};

export default UserManagementPage;
