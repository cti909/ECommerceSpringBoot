package com.example.DemoSpringBootAPI.Service.Dtos.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequest {
    private String name;
    private Boolean isDeleted;
}
