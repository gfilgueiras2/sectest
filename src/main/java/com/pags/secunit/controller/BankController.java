package com.pags.secunit.controller;

import com.pags.secunit.entity.User;
import com.pags.secunit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("bank")
@RestController
@RequiredArgsConstructor
public class BankController {

    private final UserService userService;

    @GetMapping("user/{id}")
    public User getUserAccount(@PathVariable("id") Integer id) {
        return userService.getUserInfo(id);
    }

}
