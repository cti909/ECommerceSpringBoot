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
	private String content;
	private Integer star;
	private Boolean isReview;
	private Boolean isDeleted;
    private LocalDateTime updatedAt = LocalDateTime.now();
}
