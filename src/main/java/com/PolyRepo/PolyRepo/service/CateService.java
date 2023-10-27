package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.CategoryEntity;
import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.response.CateResponse;
import com.PolyRepo.PolyRepo.payload.response.UserResponse;
import com.PolyRepo.PolyRepo.repository.CateRepository;
import com.PolyRepo.PolyRepo.service.imp.CateServiceImp;
import com.PolyRepo.PolyRepo.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CateService implements CateServiceImp {
@Autowired
    private CateRepository cateRepository;
    @Override
    public List<CateResponse> getAllCate() {
        try {
            List<CateResponse> listCate = new ArrayList<>();
            List<CategoryEntity> cate = cateRepository.findAll();
            for (CategoryEntity item :
                    cate) {
                CateResponse cates = new CateResponse();
                cates.setId(item.getId());
                cates.setName(item.getName());
                listCate.add(cates);
            }
            return listCate;
        } catch (Exception e) {
            throw new CustomException("Lỗi get all cate " + e.getMessage());
        }
    }
}
