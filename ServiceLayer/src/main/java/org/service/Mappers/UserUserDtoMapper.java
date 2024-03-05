package org.service.Mappers;

import lombok.RequiredArgsConstructor;
import org.db.Models.User;
import org.service.Dto.UserDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserUserDtoMapper implements Mapper<User, UserDto>{
    @Override
    public UserDto map(User source) {
        return UserDto.builder()
                .id(source.getId())
                .username(source.getUsername())
                .role(source.getRole())
                .banned(source.isBanned())
                .build();
    }
}
