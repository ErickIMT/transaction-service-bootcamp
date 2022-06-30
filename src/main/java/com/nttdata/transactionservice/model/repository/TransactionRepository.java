package com.nttdata.transactionservice.model.repository;

import com.nttdata.transactionservice.model.document.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

    Flux<Transaction> findAllByAccountId(String id);
}
