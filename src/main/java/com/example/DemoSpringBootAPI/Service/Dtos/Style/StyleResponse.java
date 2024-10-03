package com.example.DemoSpringBootAPI.Service.Dtos.Style;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StyleResponse
{
	private Long id;
    private String name;
    private Boolean isDeleted;
//    private List<ProductResponse> products;
}
