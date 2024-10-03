package com.example.DemoSpringBootAPI.Service.Dtos.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest
{
	private String content;
	private Integer star = 0;
	private Boolean isReview;
	private Long productId;
	private Long userId;
	private Long parentCommentId;
}
