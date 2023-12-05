package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.CommentEntity;
import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.CommentRequest;
import com.PolyRepo.PolyRepo.payload.response.CommentResponse;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
import com.PolyRepo.PolyRepo.repository.CommentRepository;
import com.PolyRepo.PolyRepo.repository.PostRepository;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.imp.CommentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentService implements CommentServiceImp {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;


    @Override
    public List<CommentResponse> getAllComments() {
        try {
            List<CommentResponse> listComment = new ArrayList<>();
            List<CommentEntity> commentEntityList = commentRepository.findAll();
            for (CommentEntity item : commentEntityList) {
                CommentResponse comment = new CommentResponse();
                comment.setId(item.getId());
                comment.setContent(item.getContent()); // Sửa lại thành getContent()
                comment.setCommentstatus(item.getCommentstatus());
                comment.setPost_id(item.getPost().getId());
                comment.setUser_id(item.getUser().getId());
                listComment.add(comment);
            }
            return listComment;
        } catch (Exception e) {
            throw new CustomException("Lỗi  " + e.getMessage());
        }
    }

    @Override
    public CommentResponse getCommentById(Integer id) {
        Optional<CommentEntity> comment = commentRepository.findById(id);
        // Chuyển đổi đối tượng Comment thành CommentResponse để trả về
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setId(comment.get().getId());
        commentResponse.setContent(comment.get().getContent());
        commentResponse.setCommentstatus(comment.get().getCommentstatus());
        commentResponse.setPost_id(comment.get().getPost().getId());
        commentResponse.setUser_id(comment.get().getUser().getId());
        return commentResponse;
    }

    @Override
    public List<CommentResponse> getCommentByPostId(int id) {
        List<CommentEntity>list=commentRepository.findByPostId(id);
        List<CommentResponse> listResponse=new ArrayList<>();
        for (CommentEntity data: list){
            CommentResponse comment=new CommentResponse();
            comment.setId(data.getId());
            comment.setContent(data.getContent()); // Sửa lại thành getContent()
            comment.setCommentstatus(data.getCommentstatus());
            comment.setPost_id(data.getPost().getId());
            comment.setUser_id(data.getUser().getId());
            listResponse.add(comment);
        }
        return listResponse;
    }

    @Override
    public CommentResponse addComment(CommentRequest commentRequest) {
        if (commentRequest.getContent().isEmpty()){
            throw new CustomException("không để trống nội dung");
        }
        try {
            CommentEntity commentEntity = new CommentEntity();
            commentEntity.setContent(commentRequest.getContent());
            commentEntity.setCommentstatus(commentRequest.getCommentstatus());

            // Tìm đối tượng PostEntity từ cơ sở dữ liệu
            PostEntity postEntity = postRepository.findById(commentRequest.getPost_id())
                    .orElseThrow(() -> new CustomException("Không tìm thấy bài viết với ID: " + commentRequest.getPost_id()));
            commentEntity.setPost(postEntity);

            // Tìm đối tượng UserEntity từ cơ sở dữ liệu
            UserEntity userEntity = userRepository.findById(commentRequest.getUser_id())
                    .orElseThrow(() -> new CustomException("Không tìm thấy người dùng với ID: " + commentRequest.getUser_id()));
            commentEntity.setUser(userEntity);

            CommentEntity savedComment = commentRepository.save(commentEntity);

            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(savedComment.getId()); // Đặt giá trị cho thuộc tính id
            commentResponse.setContent(savedComment.getContent());
            commentResponse.setCommentstatus(savedComment.getCommentstatus());
            commentResponse.setPost_id(savedComment.getPost().getId());
            commentResponse.setUser_id(savedComment.getUser().getId());

            return commentResponse;
        } catch (Exception e) {
            throw new CustomException("Lỗi " + e.getMessage());
        }
    }

    @Override
    public void deleteCommentById(Integer id) {

        CommentEntity commentEntity = commentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Không tìm thấy comment với ID: " + id));
        commentRepository.delete(commentEntity);
    }

    @Override
    public CommentResponse updateComment(Integer id, String content) {

        try {
            CommentEntity commentEntity = commentRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Không tìm thấy comment với ID: " + id));

            commentEntity.setContent(content); // Cập nhật nội dung mới

            CommentEntity updatedComment = commentRepository.save(commentEntity);

            CommentResponse commentResponse = new CommentResponse();

            commentResponse.setId(updatedComment.getId());
            commentResponse.setContent(updatedComment.getContent());
            commentResponse.setCommentstatus(updatedComment.getCommentstatus());
            commentResponse.setPost_id(updatedComment.getPost().getId());
            commentResponse.setUser_id(updatedComment.getUser().getId());

            return commentResponse;
        } catch (Exception e) {
            throw new CustomException("Lỗi  " + e.getMessage());
        }
    }
}
