package com.PolyRepo.PolyRepo.service;

import com.PolyRepo.PolyRepo.Entity.RoleEntity;
import com.PolyRepo.PolyRepo.exception.CustomException;
import com.PolyRepo.PolyRepo.payload.response.RoleResponse;
import com.PolyRepo.PolyRepo.repository.RoleRepository;
import com.PolyRepo.PolyRepo.service.imp.RoleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleService implements RoleServiceImp {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<RoleResponse> getAllRole() {
        try {
            List<RoleResponse> listRole = new ArrayList<>();
            List<RoleEntity> roleEntityList = roleRepository.findAll();
            for (RoleEntity item :
                    roleEntityList) {
                RoleResponse role = new RoleResponse();
                role.setId(item.getId());
                role.setName(item.getName());
                listRole.add(role);
            }
            return listRole;
        } catch (Exception e) {
            throw new CustomException("Lỗi getall role " + e.getMessage());
        }
    }
}
