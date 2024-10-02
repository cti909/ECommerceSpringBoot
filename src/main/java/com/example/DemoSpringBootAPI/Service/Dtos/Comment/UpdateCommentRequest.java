package com.example.DemoSpringBootAPI.Service.Dtos.Comment;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCommentRequest {
	private Long id;
	private String content;
	private String star;
	private Boolean isDeleted;
    private LocalDateTime updatedAt = LocalDateTime.now();
}
