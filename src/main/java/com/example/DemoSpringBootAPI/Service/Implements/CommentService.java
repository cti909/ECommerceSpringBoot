package com.example.DemoSpringBootAPI.Service.Implements;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.DemoSpringBootAPI.Data.Entities.Comment;
import com.example.DemoSpringBootAPI.Data.Entities.Product;
import com.example.DemoSpringBootAPI.Data.Entities.User;
import com.example.DemoSpringBootAPI.Repository.CommentRepository;
import com.example.DemoSpringBootAPI.Repository.ProductRepository;
import com.example.DemoSpringBootAPI.Repository.UserRepository;
import com.example.DemoSpringBootAPI.Service.Dtos.Comment.CommentChildResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Comment.CommentRelatedResponse;
import com.example.DemoSpringBootAPI.Service.Dtos.Comment.CreateCommentRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Comment.UpdateCommentRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationRequest;
import com.example.DemoSpringBootAPI.Service.Dtos.Pagination.PaginationResponse;
import com.example.DemoSpringBootAPI.Service.Interfaces.ICommentService;

@Service
public class CommentService implements ICommentService
{
	private final CommentRepository commentRepository;
	private final UserRepository userRepository;
	private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public CommentService(ModelMapper modelMapper, CommentRepository commentRepository, UserRepository userRepository, ProductRepository productRepository)
    {
        this.commentRepository = commentRepository;
		this.userRepository = userRepository;
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
    }

    @Override
    public List<CommentRelatedResponse> getAllComments()
    {
        return commentRepository.findAll().stream()
        		.map(comment -> {
                    CommentRelatedResponse response = modelMapper.map(comment, CommentRelatedResponse.class);
                    response.setChildren(null);
                    return response;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CommentRelatedResponse getCommentById(Long id)
    {
    	Comment comment = commentRepository.findById(id).orElse(null);
    	return comment != null ? modelMapper.map(comment, CommentRelatedResponse.class) : null;
    }

    @Override
    @Transactional
    public PaginationResponse<CommentRelatedResponse> getPaginatedRootComments(Long productId, PaginationRequest paginationRequest) {
        Pageable pageable = PageRequest.of(
                paginationRequest.getPage() - 1,
                paginationRequest.getPageSize(),
                Sort.by(Sort.Direction.fromString(paginationRequest.getSortType()), paginationRequest.getSortBy())
        );

        Page<Comment> commentPage = commentRepository.findByProductIdAndParentIsNull(productId, pageable);
        Set<Long> processedCommentIds = new HashSet<>();

        List<CommentRelatedResponse> commentRelatedResponses = commentPage.getContent().stream()
            .map(comment -> mapCommentToCommentRelatedResponse(comment, processedCommentIds))
            .collect(Collectors.toList());

        return new PaginationResponse<>(
            commentRelatedResponses,
            commentPage.getNumber() + 1,
            commentPage.getTotalPages(),
            commentPage.getTotalElements(),
            commentPage.getSize()
        );
    }

    @Override
    @Transactional
    public CommentRelatedResponse createComment(CreateCommentRequest createComment)
    {
    	Comment comment = new Comment();
    	comment.setContent(createComment.getContent());
        comment.setStar(createComment.getStar());
        comment.setIsReview(true);

        Product product = productRepository.findById(createComment.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + createComment.getProductId()));
        User user = userRepository.findById(createComment.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + createComment.getUserId()));

        comment.setProduct(product);
        comment.setUser(user);

        // comment reply
        if (createComment.getParentCommentId() != 0) {
            Comment parentComment = commentRepository.findById(createComment.getParentCommentId())
                .orElseThrow(() -> new RuntimeException("Parent comment not found with id: " + createComment.getParentCommentId()));
            comment.setParent(parentComment);
            comment.setIsReview(false);
        }
        
        // update product, non standard csdl for increased performance 
        product.setReviewTotal(product.getReviewTotal() + 1);
        product.setStarTotal(product.getStarTotal() + createComment.getStar());

        Comment commentCreated = commentRepository.save(comment);
		return commentCreated != null ? modelMapper.map(commentCreated, CommentRelatedResponse.class) : null;
    }

    @Override
    @Transactional
    public CommentRelatedResponse updateComment(Long id, UpdateCommentRequest updateCommentRequest) throws NoSuchFieldException, IllegalAccessException {
        Optional<Comment> optionalComment = commentRepository.findById(id);

        if (optionalComment.isPresent()) {
            Comment entity = optionalComment.get();

            Field[] fields = UpdateCommentRequest.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true); // access private fields
                Object newValue = field.get(updateCommentRequest);

                if (newValue != null) {
                    Field entityField = entity.getClass().getDeclaredField(field.getName());
                    entityField.setAccessible(true);
                    entityField.set(entity, newValue);
                }
            }
            commentRepository.save(entity);
            return modelMapper.map(entity, CommentRelatedResponse.class);
        } else {
            throw new RuntimeException("Comment not found with id: " + id);
        }
    }

    @Override
    public void deleteComment(Long id)
    {
//    	commentRepository.deleteById(id);
    	Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
        	Comment comment = optionalComment.get();
        	comment.setIsDeleted(true);
            commentRepository.save(comment);
        } else {
        	throw new RuntimeException("Comment not found with id: " + id);
        }
    }


//    -------------- more function------------------
    private CommentRelatedResponse mapCommentToCommentRelatedResponse(Comment comment, Set<Long> processedCommentIds) {
        if (processedCommentIds.contains(comment.getId())) {
            return null;
        }
        processedCommentIds.add(comment.getId());
        CommentRelatedResponse CommentRelatedResponse = modelMapper.map(comment, CommentRelatedResponse.class);

        List<CommentChildResponse> childResponses = comment.getChildren().stream()
            .map(child -> {
                if (!processedCommentIds.contains(child.getId())) {
                    return mapCommentToCommentChildResponse(child, processedCommentIds);
                }
                return null;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList());

        CommentRelatedResponse.setChildren(childResponses);
        return CommentRelatedResponse;
    }

    private CommentChildResponse mapCommentToCommentChildResponse(Comment child, Set<Long> processedCommentIds) {
        processedCommentIds.add(child.getId());
        return modelMapper.map(child, CommentChildResponse.class);
    }
}
