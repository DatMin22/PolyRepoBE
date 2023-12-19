package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.*;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.ShareRequest;
import com.PolyRepo.PolyRepo.payload.request.SignupRequest;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
import com.PolyRepo.PolyRepo.payload.response.ShareResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;
import com.PolyRepo.PolyRepo.repository.PostRepository;
import com.PolyRepo.PolyRepo.repository.ShareRepository;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.imp.ShareServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ShareService implements ShareServiceImp {
    @Autowired
    private ShareRepository shareRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ShareResponse> getAllShare() {
        try {
            List<ShareResponse> listShare = new ArrayList<>();
            List<ShareEntity> shareEntityList = shareRepository.findAll();
            for (ShareEntity item :
                    shareEntityList) {
                ShareResponse share = new ShareResponse();
                share.setId(item.getId());
                share.setUser_id(item.getUser().getId());
                share.setPost_id(item.getPosts().getId());
                share.setShareStatus(item.getShareStatus());
                share.setContent(item.getContent());
                listShare.add(share);
            }
            return listShare;
        } catch (Exception e) {
            throw new CustomException("Lỗi share " + e.getMessage());
        }
    }

    @Override
    public ShareResponse addShare(ShareRequest shareRequest) {
        try {
            ShareEntity shareEntity = new ShareEntity();
            shareEntity.setShareStatus(shareRequest.getShareStatus());
            shareEntity.setContent(shareRequest.getContent());
            // Tìm đối tượng PostEntity từ cơ sở dữ liệu
            PostEntity postEntity = postRepository.findById(shareRequest.getPost_id())
                    .orElseThrow(() -> new CustomException("Không tìm thấy bài viết với ID: " + shareRequest.getPost_id()));
            shareEntity.setPosts(postEntity);

            // Tìm đối tượng UserEntity từ cơ sở dữ liệu
            UserEntity userEntity = userRepository.findById(shareRequest.getUser_id())
                    .orElseThrow(() -> new CustomException("Không tìm thấy người dùng với ID: " + shareRequest.getUser_id()));
            shareEntity.setUser(userEntity);

            ShareEntity savedShare = shareRepository.save(shareEntity);

            ShareResponse shareResponse = new ShareResponse();
            shareResponse.setId(savedShare.getId()); // Đặt giá trị cho thuộc tính id
            shareResponse.setPost_id(savedShare.getPosts().getId());
            shareResponse.setUser_id(savedShare.getUser().getId());
            shareResponse.setShareStatus(savedShare.getShareStatus());
            shareResponse.setContent(savedShare.getContent());

            return shareResponse;
        } catch (Exception e) {
            throw new CustomException("Lỗi add share" + e.getMessage());
        }
    }



    @Override
    public void deleteShareByID(Integer id) {
        ShareEntity commentEntity = shareRepository.findById(id)
                .orElseThrow(() -> new CustomException("Không tìm thấy share với ID: " + id));
        shareRepository.delete(commentEntity);
    }

//

    @Override
    public ShareResponse updateShare(Integer id, String content) {
        try {
            ShareEntity shareEntity = shareRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Không tìm thấy comment với ID: " + id));

            shareEntity.setContent(content); // Cập nhật nội dung mới
            shareEntity.setId(id);

            ShareEntity updatedShare = shareRepository.save(shareEntity);

            ShareResponse shareResponse = new ShareResponse();
            shareResponse.setId(updatedShare.getId());
            shareResponse.setUser_id(updatedShare.getUser().getId());
            shareResponse.setPost_id(updatedShare.getPosts().getId());
            shareResponse.setShareStatus(updatedShare.getShareStatus());
            shareResponse.setContent(updatedShare.getContent());

            return shareResponse;
        } catch (Exception e) {
            throw new CustomException("Lỗi  " + e.getMessage());
        }
    }

    @Override
    public List<ShareResponse> getShareByUserId(int id) {
        List<ShareEntity> list = shareRepository.findByUserId(id);
        List<ShareResponse> listResponse = new ArrayList<>();
        for (ShareEntity data : list) {
            ShareResponse shareResponse = new ShareResponse();
            shareResponse.setId(data.getId());
            shareResponse.setContent(data.getContent());
            shareResponse.setUser_id(data.getUser().getId());
            shareResponse.setShareStatus(data.getShareStatus());
            shareResponse.setPost_id(data.getPosts().getId());
            listResponse.add(shareResponse);
        }
        return listResponse;
    }
}

