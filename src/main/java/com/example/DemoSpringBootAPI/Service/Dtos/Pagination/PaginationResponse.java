package com.example.DemoSpringBootAPI.Service.Dtos.Pagination;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationResponse<T> {
	private List<T> data;
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private int pageSize;
}
