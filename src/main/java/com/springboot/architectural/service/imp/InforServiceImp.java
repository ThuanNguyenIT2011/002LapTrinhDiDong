package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.InforDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.service.InforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class InforServiceImp implements InforService {
    @Autowired
    private AccountRepository accountRepository;
    @Value("${fileUpload.rootPatch}")
    private String uploadDirectory;
    private static final String UPLOAD_FOLDER = "src/main/resources/static/";

    @Override
    public InforDto updateInfor(String firstName, String lastName, String username, MultipartFile image)  {
        Optional<Account> accountOptional = accountRepository.findByUsername(username);
        if (accountOptional.isEmpty()){
            return null;
        }
        Account account = accountOptional.get();
        account.setFirstName(firstName);
        account.setLastName(lastName);
        if (!account.getAvatar().isEmpty()){
            File file = new File(UPLOAD_FOLDER + account.getAvatar());
            if (file.delete())
                System.out.println("Đã xóa ảnh cũ");
        }
        if (image != null) {
            try {
                // Lưu trữ hình ảnh vào máy chủ
                Path uploadPath = Paths.get(UPLOAD_FOLDER);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                // Lưu ảnh vào thư mục lưu trữ
                byte[] bytes = image.getBytes();
                Path imagePath = Paths.get(UPLOAD_FOLDER + File.separator + image.getOriginalFilename());
                Files.write(imagePath, bytes);
                System.out.println("file success: " + imagePath.toString());
                account.setAvatar(image.getOriginalFilename());
            } catch (IOException e) {
                // Xử lý khi có lỗi xảy ra
                e.printStackTrace();

            }
        } else {
            // Trả về mã trạng thái 400 Bad Request nếu không có hình ảnh được gửi
            return null;
        }
        try {
            accountRepository.save(account);
//            String bodyMail = "Code: " + codeVerify;
//            emailSenderService.sendSimpleEmail(signUpRequest.getUsername()+ "@student.ptithcm.edu.vn",
//                    "verification_code", bodyMail);
            return new InforDto(firstName, lastName);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }


    }
}
