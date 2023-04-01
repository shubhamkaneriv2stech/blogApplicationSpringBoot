package com.bloging.application.services;

import com.bloging.application.entityVos.CommentsVo;

public interface CommentService {

	
	CommentsVo createComment(CommentsVo commentsVo, Integer postId, Integer userId);

	void deleteComment(Integer commentId);

}
