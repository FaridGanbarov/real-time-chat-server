package practice.realtimechatserver.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import practice.realtimechatserver.dto.request.UserRequest;
import practice.realtimechatserver.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse updateUser(UserDetails userDetails, UserRequest request);
    void deleteUserById(Long id);
    void delete(String email);
    UserResponse getUser(UserDetails userDetails);
}
