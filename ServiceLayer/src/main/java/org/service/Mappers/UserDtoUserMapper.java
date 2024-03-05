package org.service.Mappers;

import lombok.RequiredArgsConstructor;
import org.db.Models.User;
import org.service.Dto.UserDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDtoUserMapper implements Mapper<UserDto, User> {
    @Override
    public User map(UserDto source) {
        return User.builder()
                .id(source.getId())
                .username(source.getUsername())
                .role(source.getRole())
                .build();
    }
}
