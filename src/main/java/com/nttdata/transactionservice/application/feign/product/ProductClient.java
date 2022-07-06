package com.nttdata.transactionservice.application.feign.product;

import com.nttdata.transactionservice.infrastructure.document.Transaction;
import com.nttdata.transactionservice.domain.dto.BankAccountDTO;
import com.nttdata.transactionservice.domain.dto.CreditDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@FeignClient(value = "product-service" , url = "localhost:8092")
public interface ProductClient {

    @GetMapping("/bankAccounts/{id}")
    BankAccountDTO getBankAccountById(@PathVariable("id") String id);

    @GetMapping("/credits/{id}")
    CreditDTO getCreditById(@PathVariable("id") String id);

    @PutMapping(value = "/bankAccounts", consumes = "application/json", produces = "application/json")
    BankAccountDTO updateBankAccountAmount(@RequestBody Transaction transaction);

    @PutMapping(value = "/credits", consumes = "application/json", produces = "application/json")
    CreditDTO updateCreditAmount(@RequestBody Transaction transaction);

}
