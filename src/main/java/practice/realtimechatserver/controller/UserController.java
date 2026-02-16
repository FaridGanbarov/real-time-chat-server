package practice.realtimechatserver.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import practice.realtimechatserver.dto.request.UserRequest;
import practice.realtimechatserver.dto.response.UserResponse;
import practice.realtimechatserver.model.admin.term.UserTerm;
import practice.realtimechatserver.repository.admin.term.UserTermRepository;
import practice.realtimechatserver.service.UserService;

import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final UserTermRepository userTermRepository;

    @GetMapping
    @Operation(summary = "Spesifik istifadəçi info-su almaq üçün endpoint. Ichinde fav product Ids saxlanilir.")
    public ResponseEntity<UserResponse> getUser(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(service.getUser(userDetails));
    }

    @GetMapping("/allUsers")
    @Operation(summary = "Bütün istifadəçiləri almaq üçün endpoint")
    public List<UserResponse> getAllUsers() {
        return service.getAllUsers();
    }

    @PutMapping("/profile/update")
    @Operation(summary = "İstifadəçi məlumatlarini güncəlləmək üçün endpoint",description = "Data form-data olaraq gondərilməlidi.Profileİmg istəyə görə param ile güncəlləyə bilərsiz.")
    public ResponseEntity<UserResponse> updateUser(@AuthenticationPrincipal UserDetails userDetails, @RequestPart(required = false,name = "request") UserRequest userRequest) {
        final var updatedUser = service.updateUser(userDetails,userRequest);
        final var location = ServletUriComponentsBuilder.fromCurrentContextPath().path("{id}").build(updatedUser.getId());
        return ResponseEntity.created(location).body(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "İstifadəçi məlumatlarini id ilə silmək üçün endpoint")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "İstifadəçi məlumatlarini email ilə silmək üçün endpoint")
    public ResponseEntity<String> deleteUser(@PathVariable String email) {
        service.delete(email);
        return ResponseEntity.ok("User deleted successfully.");
    }
    @GetMapping("/terms")
    @Operation(summary = "Privacy/Policy elde etmek ucun endpoint")

    public List<UserTerm> getUserTerm(){
        return userTermRepository.findAll();
    }
}
