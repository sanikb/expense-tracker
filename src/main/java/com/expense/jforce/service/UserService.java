package com.expense.jforce.service;

import com.expense.jforce.dto.UserDto;
import com.expense.jforce.enity.User;
import com.expense.jforce.mapper.UserMapper;
import com.expense.jforce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    // save user
    public boolean saveUser(UserDto userDto) {
        User user = userMapper.mapUserDtoToUser(userDto);
        User savedUser = userRepository.save(user);
        return savedUser != null;
    }

    // login
    public UserDto validateUser(String userName, String password) {
        User user = userRepository.findByUserNameAndPassword(userName, password);
        return (user != null) ? userMapper.mapUserToUserDto(user) : null;
    }
}
