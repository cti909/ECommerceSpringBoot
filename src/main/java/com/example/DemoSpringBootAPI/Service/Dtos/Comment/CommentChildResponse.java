package com.example.DemoSpringBootAPI.Service.Dtos.Comment;

import java.time.LocalDateTime;
import java.util.List;

import com.example.DemoSpringBootAPI.Service.Dtos.User.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentChildResponse {
    private Long id;
    private String content;
    private int star;
    private boolean isReview;
    private boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private UserResponse user;
    private List<CommentChildResponse> children;
}
