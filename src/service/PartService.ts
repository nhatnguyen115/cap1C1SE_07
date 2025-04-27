import { http } from "./Http";

export const getQuestions = async (
  partId: string | number,
  page = 0,
  size = 10,
) => {
  const res = await http.get(
    `/questions?partId=${partId}&page=${page}&size=${size}`,
  );
  return res.data.data;
};
