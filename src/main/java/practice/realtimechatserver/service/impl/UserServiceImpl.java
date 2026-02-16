package practice.realtimechatserver.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import practice.realtimechatserver.dto.request.UserRequest;
import practice.realtimechatserver.dto.response.UserResponse;
import practice.realtimechatserver.enums.Exceptions;
import practice.realtimechatserver.exception.ApplicationException;
import practice.realtimechatserver.mapper.UserMapper;
import practice.realtimechatserver.model.user.User;
import practice.realtimechatserver.repository.user.UserRepository;
import practice.realtimechatserver.service.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final UserRepository repository;


    @Override
    public List<UserResponse> getAllUsers() {
        return mapper.entityListToDtoList(repository.findAll());
    }

    @Override
    public UserResponse updateUser(UserDetails userDetails, UserRequest request) {
        User user = repository.findByEmail(userDetails.getUsername()).orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND));
        if(request!=null){
            mapper.updateEntityFromDto(request, user);
        }
        user.setUpdatedAt(LocalDateTime.now());

        repository.save(user);
        return mapper.entityToDto(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND));
        repository.deleteById(id);
    }

    @Override
    public void delete(String email) {
        Optional<User> user = repository.findByEmail(email);

        if (user.isEmpty()) {
            throw new ApplicationException(Exceptions.USER_NOT_FOUND);
        }

        repository.delete(user.get());
    }

    @Override
    public UserResponse getUser(UserDetails userDetails) {
        User user = repository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new ApplicationException(Exceptions.USER_NOT_FOUND));
        UserResponse response = mapper.entityToDto(user);
        return response;
    }
}
