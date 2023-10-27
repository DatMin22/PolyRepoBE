package com.PolyRepo.PolyRepo.controller;


import com.PolyRepo.PolyRepo.exception.Filenotfoundexception;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
@RequestMapping("")
@CrossOrigin(value = "*")
public class UploadFileController {
    //Path: chứa toàn bộ hàm hỗ trợ sẵn liên quan tới đường dẫn
    @Value("${path.root}")
    private String spath;

    @PostMapping("/uploadfile")
    public ResponseEntity<?> uploadFile(
            @RequestParam MultipartFile file
    ) {
        boolean isSuccess = false;
        //Định nghĩa đường dẫn
        Path rootPath = Paths.get(spath);//nio library
        try{
            if(!Files.exists(rootPath)){// nếu đường dẫn không tồn tại, tạo folder ứng với
                Files.createDirectory(rootPath);
            }
            String fileName = file.getOriginalFilename();
            Files.copy(file.getInputStream(),rootPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            isSuccess = true;
        }
        catch(Exception e){
            System.out.println("Loi" + e.getLocalizedMessage());
        }
        return new ResponseEntity<>(isSuccess, HttpStatus.OK);
    }
    @GetMapping("/downloadfile/{filename}")
    public ResponseEntity<?> loadFile(@PathVariable String filename){
        try {
            // đường dẫn folder root luu hinh

            Path rootpath=Paths.get(spath);
            Resource resource=new UrlResource(rootpath.resolve(filename).toUri()) ;
            if (resource.exists()) {
                //nếu tồn tại thì mới cho phép download
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
//            return new ResponseEntity<>(resource,HttpStatus.OK);
            }else {
                // khi ném exception thì code sẽ dừng và văng ra lỗi
                throw new Filenotfoundexception(200,"file not found");

            }
        }catch (Exception e) {
            throw new Filenotfoundexception(200,"file not found");

        }


    }
}
