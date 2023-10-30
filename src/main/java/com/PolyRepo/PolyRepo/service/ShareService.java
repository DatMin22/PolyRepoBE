package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.ShareEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.response.ShareResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;
import com.PolyRepo.PolyRepo.repository.ShareRepository;
import com.PolyRepo.PolyRepo.service.imp.ShareServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ShareService implements ShareServiceImp {
    @Autowired
    private ShareRepository shareRepository;



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
                share.setPost_id(item.getPosts().getId());;
                share.setShareStatus(item.getShareStatus());
                listShare.add(share);
            }
            return listShare;
        } catch (Exception e) {
            throw new CustomException("Lỗi share " + e.getMessage());
        }
    }
}

