package practice.realtimechatserver.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import practice.realtimechatserver.dto.request.UserRequest;
import practice.realtimechatserver.dto.response.UserResponse;
import practice.realtimechatserver.model.user.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    List<UserResponse> entityListToDtoList(List<User> usersList);
    UserResponse entityToDto(User user);
    User dtoToEntity(UserRequest userRequest);
    User updateEntityFromDto(UserRequest userRequest, @MappingTarget User user);
}
