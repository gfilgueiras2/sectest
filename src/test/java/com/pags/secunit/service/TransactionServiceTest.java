package com.pags.secunit.service;

import com.pags.secunit.entity.Transaction;
import com.pags.secunit.entity.User;
import dto.BaseResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private UserService userService;

    // 3 - Valide o input no valor da transacao e a excecao
    @Test
    void shouldFailTransaction_whenMoneyIsNotAvailable() throws Exception {
        Transaction transaction = Transaction.builder()
                .sentId(1)
                .receiverId(12)
                .value(100.0)
                .build();

        User userFromDb = User.builder()
                .id(1)
                .cpf(123)
                .email("teste@gmail.com")
                .money(10.0)
                .address("Rua Doutor Teste Unitario")
                .name("Joaozinho")
                .password("123")
                .addressNumber(12)
                .build();

        BaseResponse<User> userResponse = BaseResponse.<User>builder()
                .error(null)
                .response(userFromDb)
                .build();

        BaseResponse<Transaction> expectedResponse = BaseResponse.<Transaction>builder()
                .response(null)
                .error("Você não tem dinheiro!")
                .build();

        Mockito.when(userService.getUserInfo(Mockito.anyInt())).thenReturn(userResponse);

        BaseResponse<Transaction> actualReponse = transactionService.createTransaction(transaction);

        Assertions.assertEquals(expectedResponse, actualReponse);
    }

    // 4 - Valide o tamanho do valor da transacao e a excecao
    @Test
    void shouldBlock_whenInputIsHigherThanTen() throws Exception {
        Transaction transaction = Transaction.builder()
                .sentId(1)
                .receiverId(12)
                .value(1000000000.0)
                .build();

        BaseResponse<Transaction> expectedResponse = BaseResponse.<Transaction>builder()
                .error("Valor maior do que o permitido!")
                .response(null)
                .build();

        BaseResponse<Transaction> actualResponse = transactionService.createTransaction(transaction);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    // 5 - Valide o retorno das respostas dos servicos de transacao. (BOLA)
    @Test
    void shouldNotShowTransactions_whenPasswordIsIncorrect() throws Exception {
        String passwordFromHeader = "incorrectPassword";
        User userFromDb = User.builder()
                .id(1)
                .cpf(123)
                .email("teste@gmail.com")
                .money(10.0)
                .address("Rua Doutor Teste Unitario")
                .name("Joaozinho")
                .password("testPassword@123")
                .addressNumber(12)
                .build();

        BaseResponse<User> userResponse = BaseResponse.<User>builder()
                .error(null)
                .response(userFromDb)
                .build();

        Mockito.when(userService.getUserInfo(Mockito.anyInt())).thenReturn(userResponse);

        BaseResponse<List<Transaction>> expectedResponse = BaseResponse.<List<Transaction>>builder()
                .response(null)
                .error("Recurso não autorizado!")
                .build();

        BaseResponse<List<Transaction>> actualResponse = transactionService
                .allTransactionsOf(1, passwordFromHeader);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    // 6 - Indentificar um problema com autorizacao na transacao

}
