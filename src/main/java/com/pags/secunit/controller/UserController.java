package com.pags.secunit.controller;

import com.pags.secunit.entity.User;
import com.pags.secunit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{id}")
    public User getUserAccount(@PathVariable("id") Integer id) {
        return userService.getUserInfo(id);
    }

    @PostMapping()
    public User saveUser(@RequestBody User user) {
        return userService.createAccount(user);
    }
}
