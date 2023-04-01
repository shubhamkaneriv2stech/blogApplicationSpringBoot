package com.bloging.application.servicesImpl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloging.application.dao.CommentRepo;
import com.bloging.application.dao.PostRepo;
import com.bloging.application.dao.UserRepo;
import com.bloging.application.entity.Comments;
import com.bloging.application.entity.Post;
import com.bloging.application.entityVos.CommentsVo;
import com.bloging.application.exception.ResourceNotFoundException;
import com.bloging.application.services.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentsVo createComment(CommentsVo commentsVo, Integer postId, Integer userId) {

		Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));

		Comments comments = new Comments();
		comments.setContent(commentsVo.getContent());

		comments.setPosts(post);
		comments.setAddedDate(new Date());

		Comments save = commentRepo.save(comments);
		return modelMapper.map(save, CommentsVo.class);

	}

	@Override
	public void deleteComment(Integer commentId) {
		Comments comments = commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("comment", "Id", commentId));

		commentRepo.delete(comments);

	}

}
