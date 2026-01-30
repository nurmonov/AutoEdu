package org.example.autoedu.mapper;

import org.example.autoedu.dto.user.UserCreateRequest;
import org.example.autoedu.dto.user.UserResponse;
import org.example.autoedu.entity.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Create
    @Mapping(target = "id", ignore = true)
    User toEntity(UserCreateRequest dto);

    // Response
    UserResponse toResponse(User entity);
    List<UserResponse> toResponseList(List<User> entities);

    // Update — null maydonlar o'zgartirmaydi
//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "password", ignore = true)      // parol alohida yangilanadi
//    @Mapping(target = "role", ignore = true)          // rol odatda o'zgarmaydi
//    @Mapping(target = "jshshr", ignore = true)        // JSHSHIR o'zgarmasligi mumkin
//    void updateEntity(@MappingTarget User entity, UserUpdateRequest dto);
}