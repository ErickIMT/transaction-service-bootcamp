package com.nttdata.transactionservice.infrastructure.repository;

import com.nttdata.transactionservice.infrastructure.document.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

    Flux<Transaction> findAllByAccountId(String id);
}
