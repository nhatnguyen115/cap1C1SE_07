import { Dialog, Transition } from "@headlessui/react";
import React, { Fragment, useEffect, useState } from "react";
import { UserInfoType } from "../types/user";

type EditUserModalProps = {
  isOpen: boolean;
  onClose: () => void;
  onSubmit: (updatedUser: Partial<UserInfoType>) => void;
  user: UserInfoType | null;
};

const EditUserModalComponent: React.FC<EditUserModalProps> = ({
  isOpen,
  onClose,
  onSubmit,
  user,
}) => {
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");

  useEffect(() => {
    if (user) {
      setFirstName(user.firstName || "");
      setLastName(user.lastName || "");
    }
  }, [user]);

  const handleSubmit = () => {
    if (user) {
      onSubmit({
        ...user,
        firstName,
        lastName,
      });
      onClose();
    }
  };

  if (!user) return null;

  return (
    <Transition appear show={isOpen} as={Fragment}>
      <Dialog as="div" className="relative z-50" onClose={onClose}>
        <Transition.Child
          as={Fragment}
          enter="ease-out duration-300"
          enterFrom="opacity-0"
          enterTo="opacity-100"
          leave="ease-in duration-200"
          leaveFrom="opacity-100"
          leaveTo="opacity-0"
        >
          <div className="fixed inset-0 bg-black bg-opacity-30" />
        </Transition.Child>

        <div className="fixed inset-0 flex items-center justify-center p-4">
          <Dialog.Panel className="w-full max-w-md bg-white rounded-xl p-6 shadow-lg">
            <Dialog.Title className="text-lg font-medium text-gray-900 mb-4">
              Chỉnh sửa người dùng
            </Dialog.Title>
            <div className="space-y-3">
              <div>
                <label className="text-sm font-medium text-gray-700">
                  Username
                </label>
                <input
                  type="text"
                  value={user.username}
                  disabled
                  className="w-full border px-3 py-2 rounded bg-gray-100"
                />
              </div>
              <div>
                <label className="text-sm font-medium text-gray-700">
                  Email
                </label>
                <input
                  type="text"
                  value={user.email}
                  disabled
                  className="w-full border px-3 py-2 rounded bg-gray-100"
                />
              </div>
              <div>
                <label className="text-sm font-medium text-gray-700">
                  Số điện thoại
                </label>
                <input
                  type="text"
                  value={user.phoneNumber}
                  disabled
                  className="w-full border px-3 py-2 rounded bg-gray-100"
                />
              </div>
              <div>
                <label className="text-sm font-medium text-gray-700">
                  First Name
                </label>
                <input
                  type="text"
                  value={firstName}
                  onChange={(e) => setFirstName(e.target.value)}
                  className="w-full border px-3 py-2 rounded"
                />
              </div>
              <div>
                <label className="text-sm font-medium text-gray-700">
                  Last Name
                </label>
                <input
                  type="text"
                  value={lastName}
                  onChange={(e) => setLastName(e.target.value)}
                  className="w-full border px-3 py-2 rounded"
                />
              </div>
            </div>
            <div className="mt-5 flex justify-end gap-3">
              <button
                onClick={onClose}
                className="px-4 py-2 text-gray-700 border border-gray-300 rounded hover:bg-gray-100"
              >
                Hủy
              </button>
              <button
                onClick={handleSubmit}
                className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-500"
              >
                Cập nhật
              </button>
            </div>
          </Dialog.Panel>
        </div>
      </Dialog>
    </Transition>
  );
};

export default EditUserModalComponent;
