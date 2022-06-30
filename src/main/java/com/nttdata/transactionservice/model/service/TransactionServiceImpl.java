package com.nttdata.transactionservice.model.service;

import com.nttdata.transactionservice.api.product.ProductClient;
import com.nttdata.transactionservice.model.document.Transaction;
import com.nttdata.transactionservice.model.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

@Service
public class TransactionServiceImpl implements TransactionService{

    private final static Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public Mono<Transaction> create(Transaction transaction, String product) {

        LOGGER.info("Iniciando Registro de transaccion");
        if (product.equals("Credit")){
            LOGGER.info("Iniciando proceso para cuenta de credito");
            return productClient.getCreditById(transaction.getAccountId())
                    .switchIfEmpty(Mono.empty())
                    .map(creditDTO -> {
                        LOGGER.info("Se encontro cuenta de credito con id "+transaction.getAccountId());
                        LOGGER.info("Seteando fecha actual para registro");
                        transaction.setDate(Calendar.getInstance().getTime());

                        if (transaction.getTypeTransaction().equals("Deposit")){
                            LOGGER.info("Iniciando el abono con el cliente products-service");
                            productClient.updateCreditAmount(transaction);

                        }else if(transaction.getTypeTransaction().equals("Withdraw")){
                            LOGGER.info("Iniciando el retiro con el cliente products-service");
                            productClient.updateCreditAmount(transaction);
                        }

                        return transaction;
                    }).flatMap(transaction1 -> {
                        LOGGER.info("Registrando transaccion");
                        return transactionRepository.save(transaction1);
                    });
        }else if (product.equals("BankAccount")){

            LOGGER.info("Iniciando proceso para cuenta bancaria");
            return productClient.getBankAccountById(transaction.getAccountId())
                    .switchIfEmpty(Mono.empty())
                    .map(creditDTO -> {
                        LOGGER.info("Se encontro cuenta de banco con ID: "+transaction.getAccountId());
                        LOGGER.info("Seteando fecha de registro");
                        transaction.setDate(Calendar.getInstance().getTime());

                        if (transaction.getTypeTransaction().equals("Deposit")){
                            LOGGER.info("Iniciando el deposito con el cliente products-service");
                            productClient.updateBankAccountAmount(transaction);

                        }else if(transaction.getTypeTransaction().equals("Withdraw")){
                            LOGGER.info("Iniciando el retiro con el cliente products-service");
                            productClient.updateBankAccountAmount(transaction);
                        }

                        return transaction;
                    }).flatMap(transaction1 ->{
                        LOGGER.info("Registrando Transaction en la BD");
                        return transactionRepository.save(transaction1);
                    });
        }else{
            LOGGER.warn("No se recibio tipo de producto");
            return Mono.empty();
        }

    }

    @Override
    public Flux<Transaction> getAll() {

        LOGGER.info("Listando todas las transacciones");
        return transactionRepository.findAll();
    }

    @Override
    public Mono<Transaction> update(Transaction transaction) {

        return transactionRepository.save(transaction);
    }

    @Override
    public void delete(String id) {
        transactionRepository.deleteById(id);
    }

    @Override
    public Mono<Transaction> getTransaction(String id) {
        return transactionRepository.findById(id);
    }

    @Override
    public Flux<Transaction> getTransactionsById(String id) {
        return transactionRepository.findAllByAccountId(id);
    }
}
