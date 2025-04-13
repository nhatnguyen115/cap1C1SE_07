type PaginationProps = {
  currentPage: number;
  totalPages: number;
  onPageChange: (page: number) => void;
};

const PaginationComponent = ({
  currentPage,
  totalPages,
  onPageChange,
}: PaginationProps) => {
  const pages = Array.from({ length: totalPages }, (_, i) => i);

  return (
    <div className="flex gap-2 mt-4 justify-center">
      {pages.map((page) => (
        <button
          key={page}
          onClick={() => onPageChange(page)}
          className={`px-4 py-2 rounded-md border ${
            currentPage === page ? "bg-blue-500 text-white" : "bg-white"
          }`}
        >
          {page + 1}
        </button>
      ))}
    </div>
  );
};
