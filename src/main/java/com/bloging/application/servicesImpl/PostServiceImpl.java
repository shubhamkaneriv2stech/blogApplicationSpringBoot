package com.bloging.application.servicesImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bloging.application.dao.CategoriesDao;
import com.bloging.application.dao.CatergoriesRepo;
import com.bloging.application.dao.PostRepo;
import com.bloging.application.dao.UserRepo;
import com.bloging.application.entity.Categories;
import com.bloging.application.entity.Comments;
import com.bloging.application.entity.Post;
import com.bloging.application.entity.User;
import com.bloging.application.entityVos.CommentsVo;
import com.bloging.application.entityVos.PostVo;
import com.bloging.application.exception.CustomException;
import com.bloging.application.exception.DataTableListResponse;
import com.bloging.application.exception.ResourceNotFoundException;
import com.bloging.application.services.PostService;
import com.bloging.application.util.AppContants;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postReo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CatergoriesRepo catergoriesRepo;

	@Autowired
	private CategoriesDao categoriesDao;
	@Value("${project.image}")
	private String path;

	@Override
	public PostVo createPost(PostVo postVo, Integer userId, Integer categoriesId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Categories categories = catergoriesRepo.findById(categoriesId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "Id", categoriesId));

		Post post = modelMapper.map(postVo, Post.class);
		post.setImageName(AppContants.IMAGE_DEFAULT);
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategories(categories);

		Post savePost = postReo.save(post);
		return modelMapper.map(savePost, PostVo.class);
	}

	@Override
	public PostVo updatePost(PostVo postVo, Integer postId) {

		Post post = postReo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("PostId", "Id", postId));

		if (StringUtils.isNotEmpty(post.getImageName())) {
			String filePath = path + File.separator + post.getImageName();
			try {
				File file = new File(filePath);

				Path pathToFile = Paths.get(file.getPath());
				System.out.println(pathToFile.toAbsolutePath());

				if (file.getName().contains("default")) {
					System.out.println(file.getName() + " is not  deleted!");
				} else {
					cleanUp(pathToFile);
					System.out.println(file.getName() + " is deleted!");
				}
			} catch (Exception e) {
				System.out.println("Failed to Delete image !!" + e);
			}
		}

		post.setContent(postVo.getContent());
		post.setTitle(postVo.getTitle());

		if (StringUtils.isNotEmpty(postVo.getImageName())) {
			post.setImageName(postVo.getImageName());
		} else {
			post.setImageName(AppContants.IMAGE_DEFAULT);
		}

		post.setAddedDate(new Date());

		Categories categories = catergoriesRepo.findById(postVo.getCategories().getId())
				.orElseThrow(() -> new ResourceNotFoundException("category", "Id", postVo.getCategories().getId()));

		post.setCategories(categories);

		Post savePost = postReo.save(post);

		return modelMapper.map(savePost, PostVo.class);

	}

	@Override
	public void deletePost(Integer postId) {

		Post post = postReo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("PostId", "Id", postId));

		String filePath = path + File.separator + post.getImageName();

		try {
			File file = new File(filePath);

			Path pathToFile = Paths.get(file.getPath());
			System.out.println(pathToFile.toAbsolutePath());

			if (file.getName().contains("default")) {
				System.out.println(file.getName() + " is not  deleted!");
			} else {
				cleanUp(pathToFile);
				System.out.println(file.getName() + " is deleted!");
			}
		} catch (Exception e) {
			System.out.println("Failed to Delete image !!" + e);
		}
		postReo.delete(post);

	}

	private void cleanUp(Path path) throws IOException {
		System.out.println(path);
		Files.delete(path);
	}

	@Override
	public PostVo getPostById(Integer postId) {
		Post post = postReo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("postId", "Id", postId));

		List<Comments> commentsList = categoriesDao.fetchCommentsByID(postId);

		List<CommentsVo> collect = commentsList.stream().map(comments -> modelMapper.map(comments, CommentsVo.class))
				.collect(Collectors.toList());

		PostVo postVo = modelMapper.map(post, PostVo.class);
		postVo.setComments(collect);
		return postVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DataTableListResponse<PostVo> getallPost(Integer pageNumber, Integer pageSize, String sortBy,
			String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

		Pageable pageRequest = PageRequest.of(pageNumber, pageSize, sort);

		Page<Post> userList = postReo.findAll(pageRequest);

		List<Post> findAllPost = userList.getContent();

		DataTableListResponse<Post> dataTableListResponse = new DataTableListResponse();

		DataTableListResponse<PostVo> allPost = new DataTableListResponse<>();
		if (CollectionUtils.isNotEmpty(findAllPost)) {

			List<PostVo> collect = findAllPost.stream().map(post -> {

				PostVo map = modelMapper.map(post, PostVo.class);
				List<Comments> commentsList = categoriesDao.fetchCommentsByID(map.getId());

//				List<CommentsVo> collectComments = commentsList.stream()
//						.map(comments -> modelMapper.map(post, CommentsVo.class)).collect(Collectors.toList());

				List<CommentsVo> collectComments = commentsList.stream()
						.map(comments -> modelMapper.map(comments, CommentsVo.class)).collect(Collectors.toList());

				map.setComments(collectComments);
				return map;
			}).collect(Collectors.toList());

			allPost.setData(collect);

			allPost.setPageNumber(userList.getNumber());
			allPost.setPageSize(userList.getSize());
			allPost.setTotalElements(userList.getTotalElements());
			allPost.setTotalPages(userList.getTotalPages());
			allPost.setLastPage(userList.isLast());
		}

		// List<Post> findAllPost =
		// categoriesDao.fetchAllPostByCriteria(filterCriteria);
		// return findAllPost.stream().map(post -> modelMapper.map(post,
		// PostVo.class)).collect(Collectors.toList());

		return allPost;

	}

	@Override
	public DataTableListResponse<PostVo> getAllPostByUser(Integer userId, Integer pageNumber, Integer pageSize) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		Pageable pageRequest = PageRequest.of(pageNumber, pageSize);

		Page<Post> userList = postReo.findByUser(user, pageRequest);

		List<Post> findAllPost = userList.getContent();

		DataTableListResponse<Post> dataTableListResponse = new DataTableListResponse();
		DataTableListResponse<PostVo> allPost = new DataTableListResponse<>();
		if (CollectionUtils.isNotEmpty(findAllPost)) {
			allPost.setData(
					findAllPost.stream().map(post -> modelMapper.map(post, PostVo.class)).collect(Collectors.toList()));

			allPost.setPageNumber(userList.getNumber());
			allPost.setPageSize(userList.getSize());
			allPost.setTotalElements(userList.getTotalElements());
			allPost.setTotalPages(userList.getTotalPages());
			allPost.setLastPage(userList.isLast());
		}

		return allPost;

	}

	@Override
	public DataTableListResponse<PostVo> getAllPostByCategories(Integer categoriesId, Integer pageNumber,
			Integer pageSize) {

		Categories categories = catergoriesRepo.findById(categoriesId)
				.orElseThrow(() -> new ResourceNotFoundException("Categories", "Id", categoriesId));
		Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<Post> categoriesList = postReo.findByCategories(categories, pageRequest);
		List<Post> findAllPost = categoriesList.getContent();

		DataTableListResponse<Post> dataTableListResponse = new DataTableListResponse();

		DataTableListResponse<PostVo> allPost = new DataTableListResponse<>();
		if (CollectionUtils.isNotEmpty(findAllPost)) {
			allPost.setData(
					findAllPost.stream().map(post -> modelMapper.map(post, PostVo.class)).collect(Collectors.toList()));

			allPost.setPageNumber(categoriesList.getNumber());
			allPost.setPageSize(categoriesList.getSize());
			allPost.setTotalElements(categoriesList.getTotalElements());
			allPost.setTotalPages(categoriesList.getTotalPages());
			allPost.setLastPage(categoriesList.isLast());
		}

		return allPost;
	}

	@Override
	public DataTableListResponse<PostVo> searchPosts(Integer pageNumber, Integer pageSize, String keyword) {
		Pageable pageRequest = PageRequest.of(pageNumber, pageSize);
		Page<Post> postList = postReo.findByTitleContaining(keyword, pageRequest);
		List<Post> findAllPost = postList.getContent();
		if (CollectionUtils.isNotEmpty(findAllPost)) {
			DataTableListResponse<Post> dataTableListResponse = new DataTableListResponse();

			DataTableListResponse<PostVo> allPost = new DataTableListResponse<>();
			if (CollectionUtils.isNotEmpty(findAllPost)) {
				allPost.setData(findAllPost.stream().map(post -> modelMapper.map(post, PostVo.class))
						.collect(Collectors.toList()));

				allPost.setPageNumber(postList.getNumber());
				allPost.setPageSize(postList.getSize());
				allPost.setTotalElements(postList.getTotalElements());
				allPost.setTotalPages(postList.getTotalPages());
				allPost.setLastPage(postList.isLast());
			}
			return allPost;

		} else {
			throw new CustomException("Not search Found For " + keyword);
		}

	}

}
