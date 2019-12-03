package com.raf.restdemo.service;

import com.raf.restdemo.dto.TokenRequestDto;
import com.raf.restdemo.dto.TokenResponseDto;
import com.raf.restdemo.dto.UserCreateDto;
import com.raf.restdemo.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDto> findAll(Pageable pageable);

    UserDto add(UserCreateDto userCreateDto);

    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
