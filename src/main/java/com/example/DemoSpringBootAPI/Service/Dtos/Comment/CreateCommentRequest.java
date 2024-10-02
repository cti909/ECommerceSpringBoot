package com.example.DemoSpringBootAPI.Service.Dtos.Comment;

import java.time.LocalDateTime;

import com.example.DemoSpringBootAPI.Data.Entities.Product;
import com.example.DemoSpringBootAPI.Data.Entities.User;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest 
{
	private String content;
	private String star;
	private Long productId;
	private Long userId;
}
