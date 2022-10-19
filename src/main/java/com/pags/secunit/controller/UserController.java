package com.pags.secunit.controller;

import com.pags.secunit.entity.User;
import com.pags.secunit.service.UserService;
import dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{id}")
    public BaseResponse<User> getUserAccount(@PathVariable("id") Integer id) throws Exception {
        return userService.getUserInfo(id);
    }

    @PostMapping()
    public User saveUser(@RequestBody User user) {
        return userService.createAccount(user);
    }
}
