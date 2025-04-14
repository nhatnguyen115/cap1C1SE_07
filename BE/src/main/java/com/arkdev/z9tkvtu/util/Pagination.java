package com.arkdev.z9tkvtu.util;


import com.arkdev.z9tkvtu.dto.Response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class Pagination {
    public static <T> PageResponse<T> paginate(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());

        List<T> subList = list.subList(start, end);
        Page<T> page = new PageImpl<>(subList, pageable, list.size());
        return new PageResponse<>(page);
    }
}
