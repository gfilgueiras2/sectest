package com.pags.secunit.service;

import com.pags.secunit.entity.User;
import com.pags.secunit.repository.UserRepository;
import dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public BaseResponse<User> getUserInfo(Integer id) throws Exception {
        try {
            User user = userRepository.getReferenceById(id);
            String maskAddress = user.getAddress().substring(0,6);
            user.setAddress(maskAddress);
            return BaseResponse.<User>builder()
                    .response(user)
                    .error(null)
                    .build();
        } catch (Exception e) {
            return BaseResponse.<User>builder()
                    .response(null)
                    .error(e.getMessage())
                    .build();
        }
    }

    public User createAccount(User user) {
        return userRepository.save(user);
    }
}
