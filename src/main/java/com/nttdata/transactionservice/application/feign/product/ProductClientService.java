package com.nttdata.transactionservice.application.feign.product;

import com.nttdata.transactionservice.domain.dto.BankAccountDTO;
import com.nttdata.transactionservice.domain.dto.CreditDTO;
import com.nttdata.transactionservice.infrastructure.document.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class ProductClientService {
    @Autowired
    private ProductClient productClient;

    private BankAccountDTO getBankAccountById(String id){
        return productClient.getBankAccountById(id);
    }

    private CreditDTO getCreditById(String id){
        return productClient.getCreditById(id);
    }

    private CreditDTO updateCreditAmount(Transaction transaction) {
        return  productClient.updateCreditAmount(transaction);
    }
    private BankAccountDTO updateBankAccountAmount(Transaction transaction) {
        return  productClient.updateBankAccountAmount(transaction);
    }
}
