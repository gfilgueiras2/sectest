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

    // 3 - Valide o input no valor da transacao e a excecao

    // 4 - Valide o tamanho do valor da transacao e a excecao

    // 5 - Valide o retorno das respostas dos servicos de transacao
    // Dica: Todas as informacoes de retorno sao necessarias?

    // 6 - Intentifique e valide um ataque de Broken Object Level Access
}
