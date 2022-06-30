package com.nttdata.transactionservice.api.product;

import com.nttdata.transactionservice.model.dto.BankAccountDTO;
import com.nttdata.transactionservice.model.dto.CreditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class ProductClientService {
    @Autowired
    private ProductClient productClient;

    private Mono<BankAccountDTO> getBankAccountById(String id){
        return productClient.getBankAccountById(id);
    }

    private Mono<CreditDTO> getCreditById(String id){
        return productClient.getCreditById(id);
    }
}
