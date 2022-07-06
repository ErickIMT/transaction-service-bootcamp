package com.nttdata.transactionservice.infrastructure.service;

import com.nttdata.transactionservice.infrastructure.document.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Mono<Transaction> create(Transaction transaction, String product);
    Flux<Transaction> getAll();
    Mono<Transaction> update(Transaction transaction);
    void delete(String id);
    Mono<Transaction> getTransaction(String id);
    Flux<Transaction> getTransactionsByAccountId(String id);

}
