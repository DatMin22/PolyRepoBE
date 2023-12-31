package com.PolyRepo.PolyRepo.controller;

import com.PolyRepo.PolyRepo.Entity.UserEntity;
import com.PolyRepo.PolyRepo.payload.request.PasswordResetRequest;
import com.PolyRepo.PolyRepo.payload.response.BaseResponse;
import com.PolyRepo.PolyRepo.repository.UserRepository;
import com.PolyRepo.PolyRepo.service.PasswordResetService;
import com.PolyRepo.PolyRepo.service.imp.PasswordResetServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/password")
@CrossOrigin("*")
public class PasswordController {

    @Autowired
    private PasswordResetService passwordResetService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordResetServiceImp passwordResetServiceImp;
    @PostMapping("/reset")
    public ResponseEntity<BaseResponse> resetPassword(
            @RequestBody PasswordResetRequest request
    ) {
        BaseResponse response = new BaseResponse();
        // Lấy địa chỉ email của người dùng
        String email = request.getEmail();
        System.out.println("email" + email);
        Optional<UserEntity> existingUserWithEmail = userRepository.findOneByEmailIgnoreCase(email);
        if (existingUserWithEmail.isPresent()) {
            // Tạo mã thông báo đặt lại mật khẩu
            String token = passwordResetService.generatePasswordResetToken();


            passwordResetService.sendPasswordResetMail(email, token);
            passwordResetService.savePasswordResetToken(token, email);


            // Tạo đối tượng BaseResponse
            response.setMessage("Một email đã được gửi đến địa chỉ email của bạn. Vui lòng kiểm tra hộp thư đến của bạn.");
            response.setStatusCode(200);
            response.setData(new HashMap<String, String>() {{
                put("email", email);
                put("token", token);
            }});

            // Trả về đối tượng BaseResponse
            System.out.println(response);
            return ResponseEntity.ok(response);
        }else {
            response.setMessage("Email không tồn tại");

        }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/change")
    public ResponseEntity<BaseResponse> changePassword(
            @RequestBody PasswordResetRequest request) {

        BaseResponse response = new BaseResponse();

        // Kiểm tra mã xác nhận
        if(!passwordResetServiceImp.validatePasswordResetToken(request.getToken())) {
            response.setMessage("Mã xác nhận không hợp lệ");
            return ResponseEntity.badRequest().body(response);
        }

        // Lấy thông tin người dùng từ CSDL
        String token = request.getToken();
        UserEntity user = userRepository.findByResetToken(token);

        // Đổi mật khẩu

        if(request.getNewPassword().length() < 8){
            response.setMessage("Mật khẩu phải trên 8 kí tự");
            return ResponseEntity.badRequest().body(response);
        }
        // Đổi mật khẩu
        passwordResetService.changePassword(request.getToken(), request.getNewPassword());
        // Xóa mã xác nhận
        passwordResetService.deletePasswordResetToken(request.getToken());

        response.setMessage("Đổi mật khẩu thành công");
        return ResponseEntity.ok(response);

    }

}
