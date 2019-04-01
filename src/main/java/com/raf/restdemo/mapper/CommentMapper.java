package com.raf.restdemo.mapper;

import com.raf.restdemo.domain.Comment;
import com.raf.restdemo.domain.Product;
import com.raf.restdemo.dto.CommentDto;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDto commentToCommentDto(Comment comment) {
        return CommentDto.builder()
                .text(comment.getText())
                .user(comment.getUser())
                .productRating(comment.getProductRating())
                .id(comment.getId())
                .build();
    }

    public Comment commentDtoToComment(CommentDto commentDto, Product product) {
        return Comment.builder()
                .text(commentDto.getText())
                .user(commentDto.getUser())
                .productRating(commentDto.getProductRating())
                .product(product)
                .id(commentDto.getId())
                .build();
    }

}
