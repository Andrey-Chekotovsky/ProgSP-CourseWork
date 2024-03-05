package org.service.Service;

import lombok.RequiredArgsConstructor;
import org.db.JpaRepositories.CompanyUserRelationsRepository;
import org.db.JpaRepositories.UserRepository;
import org.db.Models.Company;
import org.db.Models.CompanyUserRelations;
import org.db.Models.User;
import org.service.Dto.*;
import org.service.Mappers.Mapper;
import org.service.Mappers.RegisterDtoToUserMapper;
import org.service.Mappers.UserUserDtoMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final Mapper<User, UserDetailsDto> userUserDetailsDtoMapper;
    private final Mapper<User, UserDto> userUserDtoMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mapper<RegisterRequestDto, User> userMapper;
    private final CompanyUserRelationsRepository companyUserRelationsRepository;
    private  final  Mapper<CompanyUserRelationsDto, CompanyUserRelations> companyUserRelationsDtoMapper;
    private  final Mapper<UserDto, User> userDtoUserMapper;


    public UserDetailsDto getUser(int id) {
        User user = userRepository.getById(id);
        return userUserDetailsDtoMapper.map(user);
    }
    public List<UserDto> getUsers() {
        var users = userRepository.findAll();
        return users.stream().map(userUserDtoMapper::map).toList();
    }
    public User register(RegisterRequestDto dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User user = userMapper.map(dto);
        return userRepository.save(user);
    }
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    public void updateUser(UserDto dto) {
        var user = userDtoUserMapper.map(dto);
        userRepository.save(user);
    }
    public UserDto createUser(User User) {
        return userUserDtoMapper.map(userRepository.save(User));
    }

    public void subscribe(CompanyUserRelationsDto dto) {
        companyUserRelationsRepository.save(
                companyUserRelationsDtoMapper.map(dto)
        );
    }
    public void unsubscribe(CompanyUserRelationsDto dto) {
        companyUserRelationsRepository.delete(
                companyUserRelationsDtoMapper.map(dto)
        );
    }
    public void ban(int id) {
        User user = userRepository.getReferenceById(id);
        user.setBanned(true);
        userRepository.save(user);
    }
    public void grantAuthority(AuthorityDto dto) {
        User user = userRepository.getReferenceById(dto.getUserId());
        user.setRole(dto.getRole());
        user.setCompany(dto.getCompanyId() == 0 ? null : Company.builder()
                        .id(dto.getCompanyId())
                .build());
        userRepository.save(user);
    }

}
