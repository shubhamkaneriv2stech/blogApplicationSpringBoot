package com.bloging.application.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bloging.application.entityVos.CommentsVo;
import com.bloging.application.services.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/user/{userId}/create-comment")
	public ResponseEntity<CommentsVo> createComment(@Valid @RequestBody CommentsVo commentsVo,
			@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId) {

		CommentsVo createcomment = commentService.createComment(commentsVo, postId, userId);

		return new ResponseEntity<>(createcomment, HttpStatus.CREATED);
	}

	@DeleteMapping("/{commentId}")
	public ResponseEntity<String> deleteById(@PathVariable("commentId") Integer commentId) {
		commentService.deleteComment(commentId);
		return new ResponseEntity<>("Comment Deleted With Id " + commentId, HttpStatus.OK);
	}
}
