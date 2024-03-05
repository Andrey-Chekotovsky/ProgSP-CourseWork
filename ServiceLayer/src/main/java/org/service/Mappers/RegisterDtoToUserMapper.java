package org.service.Mappers;

import lombok.RequiredArgsConstructor;
import org.db.Models.User;
import org.service.Dto.RegisterRequestDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterDtoToUserMapper implements Mapper<RegisterRequestDto, User> {
    public User map(RegisterRequestDto dto) {
        User user = new User().toBuilder()
                .username(dto.getUsername())
                .password(String.valueOf(dto.getPassword()))
                .role(dto.getRole())
                .build();
        return user;
    }
}
