package com.expense.jforce.mapper;

import com.expense.jforce.dto.UserDto;
import com.expense.jforce.enity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User mapUserDtoToUser(UserDto userDto){
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFullName(userDto.getFullName());

        return user;
    }

    public UserDto mapUserToUserDto(User user){
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setFullName(user.getFullName());

        return userDto;
    }
}
