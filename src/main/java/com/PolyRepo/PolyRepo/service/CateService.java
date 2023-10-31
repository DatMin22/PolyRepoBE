package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.CategoryEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.CateRequest;
import com.PolyRepo.PolyRepo.payload.response.CateResponse;
import com.PolyRepo.PolyRepo.repository.CateRepository;
import com.PolyRepo.PolyRepo.service.imp.CateServiceImp;
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

    @Override
    public CateResponse addCate(CateRequest cateRequest) {

        try {
            CategoryEntity category = new CategoryEntity();
            category.setName(cateRequest.getName());
            CategoryEntity savedCate = cateRepository.save(category);
            CateResponse cateResponse = new CateResponse();
            cateResponse.setId(savedCate.getId());
            cateResponse.setName(savedCate.getName());

            return cateResponse;
        } catch (Exception e) {
            throw new CustomException("Lỗi add Cate" + e.getMessage());

        }
    }
    @Override
    public boolean deletecate(int Id) {
        boolean isSuccess = false;
        try {
            System.out.println("timid");
            CategoryEntity cate = cateRepository.findById(Id);
            cateRepository.delete(cate);
            isSuccess = true;
        } catch (Exception e) {
            throw new CustomException("Lỗi delete category " + e.getMessage());
        }
        return isSuccess;
    }

}
