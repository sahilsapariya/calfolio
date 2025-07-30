package com.example.calfolio_backend.service;

import com.example.calfolio_backend.dto.UserDto;
import com.example.calfolio_backend.entity.User;

public interface UserService {
    User createUser(UserDto userDto);
    User loginUser(String email, String password);
    User getUserById(Long id);
}
