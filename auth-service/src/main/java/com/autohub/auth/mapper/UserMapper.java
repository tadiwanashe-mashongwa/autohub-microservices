package com.autohub.auth.mapper;

import com.autohub.auth.dto.UserDto;
import com.autohub.auth.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring") // Tells MapStruct to create a Spring Bean
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    // This method will automatically map fields from User -> UserDto
    UserDto userToUserDto(User user);
}