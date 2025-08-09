package com.example.calfolio.service;

import com.example.calfolio.dto.UserDto;
import com.example.calfolio.entity.User;

public interface UserService {
    User createUser(UserDto userDto);
    User loginUser(String email, String password);
    User getUserById(Long id);
}
