package com.arkdev.z9tkvtu.dto.request;

import lombok.Data;

@Data
public class PagingRequest {

    private Integer page = 0;
    private Integer size = 10;
}
