package com.edu.ulab.app.service.impl;

import com.edu.ulab.app.dto.UserDto;
import com.edu.ulab.app.entity.User;
import com.edu.ulab.app.mapper.UserMapperImpl;
import com.edu.ulab.app.service.UserService;
import com.edu.ulab.app.storage.impl.UserRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepositoryImpl userRepository;
    private final UserMapperImpl userMapper;

    public UserServiceImpl(UserRepositoryImpl userRepository, UserMapperImpl userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        // сгенерировать идентификатор
        // создать пользователя
        // вернуть сохраненного пользователя со всеми необходимыми полями id
        User createdUser = userRepository.create(userMapper.userDtoToUser(userDto));
        return userMapper.userToUserDto(createdUser);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User updatedUser = userRepository.update(userMapper.userDtoToUser(userDto));
        return userMapper.userToUserDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Long id) {
        UserDto foundUserById = userMapper.userToUserDto(userRepository.getById(id));
        return foundUserById;
    }

    @Override
    public UserDto deleteUserById(Long id) {
        UserDto deletedUser = userMapper.userToUserDto(userRepository.deleteById(id));
        return deletedUser;
    }
}
