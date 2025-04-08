package com.arkdev.z9tkvtu.util;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class Pagination {
    public static <T> Page<T> paginate(List<T> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());

        List<T> subList = list.subList(start, end);
        return new PageImpl<T>(subList, pageable, list.size());
    }
}
