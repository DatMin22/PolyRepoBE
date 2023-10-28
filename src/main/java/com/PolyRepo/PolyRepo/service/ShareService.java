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
            List<ShareResponse> listUser = new ArrayList<>();
            List<ShareEntity> shareEntityList = shareRepository.findAll();
            for (ShareEntity item :
                    shareEntityList) {
                ShareResponse share = new ShareResponse();
                share.setUser(item.getUsers().getId());
                share.setPosts(item.getPosts().getId());

                listUser.add(share);
            }
            return listUser;
        } catch (Exception e) {
            throw new CustomException("Lỗi share " + e.getMessage());
        }
    }
}

