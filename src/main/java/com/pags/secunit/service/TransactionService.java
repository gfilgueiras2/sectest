package com.pags.secunit.service;

import com.pags.secunit.entity.Transaction;
import com.pags.secunit.entity.User;
import com.pags.secunit.repository.TransactionRepository;
import dto.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;

    private final UserService userService;

    public BaseResponse<Transaction> createTransaction(Transaction transaction, String password) throws Exception {
        // filters trasnaction value
        if(transaction.getValue() > 1000000.0) {
            return BaseResponse.<Transaction>builder()
                    .response(null)
                    .error("Valor maior do que o permitido!")
                    .build();
        }
        // checks amount x user available money
        User sender = userService.getUserInfo(transaction.getSentId()).getResponse();
        User receiver = userService.getUserInfo(transaction.getReceiverId()).getResponse();
        if(sender == null && receiver == null) {
            return BaseResponse.<Transaction>builder()
                    .response(null)
                    .error("Usuario não existe!")
                    .build();
        } else {
            if(Objects.equals(sender.getPassword(), password)) {
                if(sender.getMoney() >= transaction.getValue()) {
                    // subtract the money from the sender and add to the receiver account...

                    // return transaction response
                    return BaseResponse.<Transaction>builder()
                            .response(transactionRepository.save(transaction))
                            .error(null)
                            .build();
                } else {
                    return BaseResponse.<Transaction>builder()
                            .response(null)
                            .error("Você não tem dinheiro!")
                            .build();
                }
            }
        }
        return BaseResponse.<Transaction>builder()
                .response(null)
                .error("Não autorizado!")
                .build();
    }

    public Transaction getTransaction(Integer id) {
        return transactionRepository.getReferenceById(id);
    }

    public BaseResponse<List<Transaction>> allTransactionsOf(Integer id, String password) throws Exception {
        User userData = userService.getUserInfo(id).getResponse();
        if(userData != null) {
            if (Objects.equals(userData.getPassword(), password)) {
                return BaseResponse.<List<Transaction>>builder()
                        .response(transactionRepository.allTransactionsOf(id))
                        .error(null)
                        .build();
            } else {
                return BaseResponse.<List<Transaction>>builder()
                        .response(null)
                        .error("Recurso não autorizado!")
                        .build();
            }
        }
        return BaseResponse.<List<Transaction>>builder()
                .response(null)
                .error("Usuario não existe!")
                .build();
    }
}
