package com.example.DemoSpringBootAPI.Service.Interfaces;

import java.util.List;

import com.example.DemoSpringBootAPI.Service.Dtos.Comment.CommentRelatedResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Comment.CreateCommentRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Comment.UpdateCommentRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationResponse;

public interface ICommentService {
	public List<CommentRelatedResponse> getAllComments();
	public CommentRelatedResponse getCommentById(Long id);
	public PaginationResponse<CommentRelatedResponse> getPaginatedRootComments(Long productId, PaginationRequest paginationRequest);
	public CommentRelatedResponse createComment(CreateCommentRequest createComment);
	public CommentRelatedResponse updateComment(Long id, UpdateCommentRequest updateComment) throws NoSuchFieldException, IllegalAccessException;
	public void deleteComment(Long id);
}
