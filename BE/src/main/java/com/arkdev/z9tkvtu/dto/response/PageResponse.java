package com.arkdev.z9tkvtu.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageResponse<T> {
    List<T> items;
    int totalPages;
    long totalElements;
    int pageNumber;
    int pageSize;

    public PageResponse(Page<T> page) {
        items = page.getContent();
        totalPages = page.getTotalPages();
        totalElements = page.getTotalElements();
        pageNumber = page.getNumber();
        pageSize = page.getSize();
    }
}
