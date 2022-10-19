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
            // Verificacao de endereco nulo
            if(user.getAddress() != null) {
                String maskAddress = user.getAddress().substring(0,6);
                user.setAddress(maskAddress);
            }
            return BaseResponse.<User>builder()
                    .response(user)
                    .error(null)
                    .build();
        } catch (Exception e) {
            return BaseResponse.<User>builder()
                    .response(null)
                    // Erro mais generico
                    .error("Algo de errado aconteceu. Entre em contato com o suporte")
                    .build();
        }
    }

    public User createAccount(User user) {
        return userRepository.save(user);
    }
}
