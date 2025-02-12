package com.example.msauserdemo.service;

import com.example.msauserdemo.dto.UserDto;
import com.example.msauserdemo.entity.UserEntity;
import com.example.msauserdemo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void createUser(@Valid UserDto userDto) {
        if( userDto.getEmail() == null || userDto.getEmail().isEmpty() ) {
            throw new IllegalArgumentException("이메일을 입력해야 합니다");
        }
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 가입한 이메일입니다");
        }
        if( userDto.getUserName() == null || userDto.getUserName().isEmpty() ) {
            throw new IllegalArgumentException("이름을 입력하시오");
        }
        if( userDto.getPassword() == null || userDto.getPassword().isEmpty() ) {
            throw new IllegalArgumentException("비밀번호를 입력하시오");
        }

        UserEntity userEntity = UserEntity.builder()
                .email(userDto.getEmail())
                .userName(userDto.getUserName())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .roles(userDto.getRoles())
                .enable(false)
                .build();

        userRepository.save(userEntity);

        sendValidEmail(userEntity);
    }
    private void sendValidEmail(UserEntity userEntity) {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(token, userEntity.getEmail(),1, TimeUnit.DAYS);

    }
}
