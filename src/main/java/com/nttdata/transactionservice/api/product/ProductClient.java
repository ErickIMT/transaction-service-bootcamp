package com.nttdata.transactionservice.api.product;

import com.nttdata.transactionservice.model.document.Transaction;
import com.nttdata.transactionservice.model.dto.BankAccountDTO;
import com.nttdata.transactionservice.model.dto.CreditDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@ReactiveFeignClient(value = "product-service" , url = "localhost:8092")
public interface ProductClient {

    @GetMapping("/bankAccounts/{id}")
    Mono<BankAccountDTO> getBankAccountById(@PathVariable("id") String id);

    @GetMapping("/credits/{id}")
    Mono<CreditDTO> getCreditById(@PathVariable("id") String id);

    @PutMapping("/bankAccounts")
    Mono<BankAccountDTO> updateBankAccountAmount(@RequestBody Transaction transaction);

    @PutMapping("/credits")
    Mono<CreditDTO> updateCreditAmount(@RequestBody Transaction transaction);

}
