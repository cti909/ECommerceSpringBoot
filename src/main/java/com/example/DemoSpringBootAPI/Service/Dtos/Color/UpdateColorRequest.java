package com.example.DemoSpringBootAPI.Service.Dtos.Color;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateColorRequest {
    private String name;
    private String color;
    private Boolean isDeleted;
}
