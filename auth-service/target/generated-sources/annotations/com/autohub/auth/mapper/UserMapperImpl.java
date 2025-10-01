package com.autohub.auth.mapper;

import com.autohub.auth.dto.UserDto;
import com.autohub.auth.entity.User;
import com.autohub.auth.enums.Role;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-24T09:06:17+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UUID userId = null;
        String name = null;
        String email = null;
        Role role = null;

        userId = user.getUserId();
        name = user.getName();
        email = user.getEmail();
        role = user.getRole();

        UserDto userDto = new UserDto( userId, name, email, role );

        return userDto;
    }
}
