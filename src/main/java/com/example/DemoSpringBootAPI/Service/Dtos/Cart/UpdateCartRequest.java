package com.example.DemoSpringBootAPI.Service.Dtos.Cart;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCartRequest {
	private Integer quantity;
    private LocalDateTime updatedAt = LocalDateTime.now();
}
