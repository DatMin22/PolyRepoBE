package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.CategoryEntity;
import com.PolyRepo.PolyRepo.Entity.PostEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.request.CateRequest;
import com.PolyRepo.PolyRepo.payload.response.CateResponse;
import com.PolyRepo.PolyRepo.payload.response.PostResponse;
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
                cates.setShorts(item.getShorts());
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
            category.setShorts(cateRequest.getShorts());
            CategoryEntity savedCate = cateRepository.save(category);
            CateResponse cateResponse = new CateResponse();
            cateResponse.setId(savedCate.getId());
            cateResponse.setName(savedCate.getName());
            cateResponse.setShorts(savedCate.getShorts());


            return cateResponse;
        } catch (Exception e) {
            throw new CustomException("Lỗi add Cate" + e.getMessage());

        }
    }

    @Override
    public void deleteCatetById(Integer id) {
        CategoryEntity category = cateRepository.findById(id)
                .orElseThrow(() -> new CustomException("Không tìm thấy cate với ID: " + id));
        cateRepository.delete(category);
    }

    @Override
    public CateResponse updateCate(Integer id, String name, String shorts) {
        try {
            CategoryEntity categoryEntity = cateRepository.findById(id)
                    .orElseThrow(() -> new CustomException("Không tìm thấy cate với ID: " + id));

            categoryEntity.setName(name); // Cập nhật nội dung mới
            categoryEntity.setShorts(shorts);

            CategoryEntity updatedCate = cateRepository.save(categoryEntity);

            CateResponse cateResponse = new CateResponse();
            cateResponse.setId(updatedCate.getId());
            cateResponse.setShorts(updatedCate.getShorts());
            cateResponse.setName(updatedCate.getName());

            return cateResponse;
        } catch (Exception e) {
            throw new CustomException("Lỗi  " + e.getMessage());
        }
    }


}
