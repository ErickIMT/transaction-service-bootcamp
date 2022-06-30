package com.nttdata.transactionservice.api;

import com.nttdata.transactionservice.model.document.Transaction;
import com.nttdata.transactionservice.model.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Clase Controladora de las transacciones bancarias.
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

  @Autowired
  private TransactionService transactionService;

  @GetMapping
  private Flux<Transaction> getAllTransactions() {
    return transactionService.getAll();
  }

  @GetMapping("/{id}")
  private Flux<Transaction> getTransactionsById(@PathVariable("id") String id) {
    return transactionService.getTransactionsById(id);
  }

  @PostMapping("/{product}")
  private Mono<Transaction> createTransaction(@RequestBody Transaction transaction,
                                              @PathVariable("product") String product) {
    return transactionService.create(transaction, product);
  }



}
