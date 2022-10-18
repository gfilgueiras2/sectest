package com.pags.secunit.service;

import com.pags.secunit.entity.User;
import com.pags.secunit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserInfo(Integer id) {
        return userRepository.getReferenceById(id);
    }
}
