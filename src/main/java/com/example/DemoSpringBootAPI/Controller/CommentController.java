package com.example.DemoSpringBootAPI.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DemoSpringBootAPI.Service.Dtos.Comment.CommentRelatedResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Comment.CreateCommentRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Comment.UpdateCommentRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationResponse;
import com.example.DemoSpringBootAPI.Service.Interfaces.ICommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	private final ICommentService commentService;

	public CommentController(ICommentService commentService) {
		this.commentService = commentService;
	}

	// fix
	@GetMapping
	public ResponseEntity<?> getAllComments() {
		try {
			var comments = commentService.getAllComments();
			return ResponseEntity.ok(comments);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCommentById(@PathVariable("id") Long id) {
		try {
			var comment = commentService.getCommentById(id);
			return ResponseEntity.ok(comment);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/product/{productId}")
    public ResponseEntity<?> getCommentsByProduct(@PathVariable("productId") Long productId, PaginationRequest paginationRequest) {

        PaginationResponse<CommentRelatedResponse> commentResponses = commentService.getPaginatedRootComments(productId, paginationRequest);
        return new ResponseEntity<>(commentResponses, HttpStatus.OK);
    }

	@PostMapping
	public ResponseEntity<?> createComment(@RequestBody CreateCommentRequest createComment) {
		try {
			var comment = commentService.createComment(createComment);
			return ResponseEntity.ok(comment);
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

    @PutMapping("/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id, @RequestBody UpdateCommentRequest updateCommentRequest) {
    	try
		{
    		CommentRelatedResponse updatedComment = commentService.updateComment(id, updateCommentRequest);
            return ResponseEntity.ok(updatedComment);
		}
		catch (Exception ex)
		{
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
		}

    }

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) {
		try {
			commentService.deleteComment(id);
			return ResponseEntity.ok(true);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}
}
