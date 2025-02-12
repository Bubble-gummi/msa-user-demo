package com.example.msauserdemo.controller;

import com.example.msauserdemo.dto.UserDto;
import com.example.msauserdemo.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody UserDto userDto) {
        System.out.println("회원가입요청 : " + userDto.toString());
        userService.createUser( userDto);
        return ResponseEntity.ok("회원가입 성공");
    }

}
