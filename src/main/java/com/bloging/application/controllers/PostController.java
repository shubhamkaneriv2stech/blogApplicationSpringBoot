package com.bloging.application.controllers;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bloging.application.entityVos.PostVo;
import com.bloging.application.exception.DataTableListResponse;
import com.bloging.application.services.FileService;
import com.bloging.application.services.PostService;
import com.bloging.application.util.AppContants;

@RestController
@RequestMapping("/api/post")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/user/{userId}/category/{catrgoryId}/create-post")
	public ResponseEntity<PostVo> createPost(@Valid @RequestBody PostVo postVo, @PathVariable("userId") Integer userId,
			@PathVariable("catrgoryId") Integer catrgoryId) {

		PostVo createPost = postService.createPost(postVo, userId, catrgoryId);

		return new ResponseEntity<>(createPost, HttpStatus.CREATED);
	}

	@GetMapping("/getAllPost")
	public ResponseEntity<DataTableListResponse<PostVo>> allPost(
			@RequestParam(value = "pageNumber", defaultValue = AppContants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppContants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppContants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppContants.SORT_DIR, required = false) String sortDir) {

		DataTableListResponse<PostVo> allPostByCategories = postService.getallPost(pageNumber, pageSize, sortBy,
				sortDir);

		return new ResponseEntity<>(allPostByCategories, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/getAllPost")
	public ResponseEntity<DataTableListResponse<PostVo>> allPostByUser(@PathVariable("userId") Integer userId,
			@RequestParam(value = "pageNumber", defaultValue = AppContants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppContants.PAGE_SIZE, required = false) Integer pageSize) {

		DataTableListResponse<PostVo> allPostByUser = postService.getAllPostByUser(userId, pageNumber, pageSize);
		return new ResponseEntity<>(allPostByUser, HttpStatus.OK);

	}

	@GetMapping("/category/{catrgoryId}/getAllPost")
	public ResponseEntity<DataTableListResponse<PostVo>> allPostByCategories(
			@PathVariable("catrgoryId") Integer catrgoryId,
			@RequestParam(value = "pageNumber", defaultValue = AppContants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppContants.PAGE_SIZE, required = false) Integer pageSize) {

		DataTableListResponse<PostVo> allPostByCategories = postService.getAllPostByCategories(catrgoryId, pageNumber,
				pageSize);

		return new ResponseEntity<>(allPostByCategories, HttpStatus.OK);
	}

	@GetMapping("/getPost/{postId}")
	public ResponseEntity<PostVo> postById(@PathVariable("postId") Integer postId) {

		PostVo postById = postService.getPostById(postId);

		return new ResponseEntity<>(postById, HttpStatus.OK);
	}

	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostVo> updatePostById(@Valid @RequestBody PostVo postVo,
			@PathVariable("postId") Integer postId) {
		PostVo updateePost = postService.updatePost(postVo, postId);
		return new ResponseEntity<>(updateePost, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable("postId") Integer postId) {

		postService.deletePost(postId);

		return new ResponseEntity<>("Post Deleted With Id " + postId, HttpStatus.OK);
	}

	@GetMapping("/searchTitle")
	public ResponseEntity<DataTableListResponse<PostVo>> allPostSearch(
			@RequestParam(value = "title", required = true) String title,
			@RequestParam(value = "pageNumber", defaultValue = AppContants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppContants.PAGE_SIZE, required = false) Integer pageSize) {

		DataTableListResponse<PostVo> allPostByCategories = postService.searchPosts(pageNumber, pageSize, title);

		return new ResponseEntity<>(allPostByCategories, HttpStatus.OK);
	}

	// post image upload

	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostVo> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId) throws IOException {

		PostVo postDto = this.postService.getPostById(postId);

		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
		PostVo updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<>(updatePost, HttpStatus.OK);

	}

	// method to serve files
	@GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response)
			throws IOException {

		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource, response.getOutputStream());

	}
}
