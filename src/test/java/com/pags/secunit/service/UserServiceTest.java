package com.pags.secunit.service;

import com.pags.secunit.entity.User;
import com.pags.secunit.repository.UserRepository;
import dto.BaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    // 1 - Trate um lancamento de excecoes
    @Test
    void shouldMaskUserAddress_whenExists() throws Exception {
        User userFromDb = User.builder()
                .id(1)
                .cpf(123)
                .email("teste@gmail.com")
                .money(100.0)
                .address("rua dos laranjais")
                .name("Joaozinho")
                .password("123")
                .addressNumber(12)
                .build();

        Mockito.when(userRepository.getReferenceById(Mockito.anyInt())).thenReturn(userFromDb);

        BaseResponse<User> expected = userService.getUserInfo(1);

        Assertions.assertEquals(expected.getResponse().getAddress(), "rua do");
    }

    @Test
    void shouldJumpMasking_whenAddressIsNull() throws Exception {
        User userFromDb = User.builder()
                .id(1)
                .cpf(123)
                .email("teste@gmail.com")
                .money(100.0)
                .address(null)
                .name("Joaozinho")
                .password("123")
                .addressNumber(12)
                .build();

        Mockito.when(userRepository.getReferenceById(Mockito.anyInt())).thenReturn(userFromDb);

        BaseResponse<User> expected = userService.getUserInfo(1);

        Assertions.assertNull(expected.getResponse().getAddress());
    }

    // 2 - Valide uma entrada de senha do usuario para se registrar com a excecao

    @Test
    void shouldBlockPassword_WhenIsWeak() {
        User newUser = User.builder()
                .id(1)
                .cpf(123)
                .email("teste@gmail.com")
                .money(100.0)
                .address("rua engenheiro jose antonio")
                .name("Joaozinho")
                .password("123")
                .addressNumber(12)
                .build();

        BaseResponse<User> expected = userService.createAccount(newUser);

        Assertions.assertEquals(expected.getError(), "Revise a senha. A senha informada é muito fraca.");
    }
}
