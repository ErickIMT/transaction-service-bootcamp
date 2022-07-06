package com.nttdata.transactionservice.infrastructure.service;

import com.nttdata.transactionservice.application.feign.product.ProductClient;
import com.nttdata.transactionservice.domain.dto.BankAccountDTO;
import com.nttdata.transactionservice.domain.dto.CreditDTO;
import com.nttdata.transactionservice.infrastructure.document.Transaction;
import com.nttdata.transactionservice.infrastructure.repository.TransactionRepository;

import java.util.Calendar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


/**
 * Servicio controlador de las operaciones con transacciones.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private ProductClient productClient;

  @Override
  public Mono<Transaction> create(Transaction transaction, String product) {

    LOGGER.info("Iniciando Registro de transaccion");
    if (product.equals("Credit")) {
      LOGGER.info("Iniciando proceso para cuenta de credito");

      CreditDTO creditDTO = productClient.getCreditById(transaction.getAccountId());

      if (!creditDTO.equals(null)) {
        LOGGER.info("Se encontro cuenta de credito con id " + transaction.getAccountId());
        LOGGER.info("Seteando fecha actual para registro");
        transaction.setDate(Calendar.getInstance().getTime());

        if (transaction.getTypeTransaction().equals("Deposit")) {
          LOGGER.info("Iniciando el abono con el cliente products-service");
          productClient.updateCreditAmount(transaction);

        } else if (transaction.getTypeTransaction().equals("Withdraw")) {
          LOGGER.info("Iniciando el retiro con el cliente products-service");
          productClient.updateCreditAmount(transaction);
        }
      }

    } else if (product.equals("BankAccount")) {
      LOGGER.info("Iniciando proceso para cuenta bancaria");
      BankAccountDTO bankAccountDTO = productClient.getBankAccountById(transaction.getAccountId());

      if (!bankAccountDTO.equals(null)) {
        LOGGER.info("Se encontro cuenta de banco con ID: " + transaction.getAccountId());
        LOGGER.info("Seteando fecha de registro");
        transaction.setDate(Calendar.getInstance().getTime());

        if (transaction.getTypeTransaction().equals("Deposit")) {
          LOGGER.info("Iniciando el deposito con el cliente products-service");
          productClient.updateBankAccountAmount(transaction);

        } else if (transaction.getTypeTransaction().equals("Withdraw")) {
          LOGGER.info("Iniciando el retiro con el cliente products-service");
          productClient.updateBankAccountAmount(transaction);
        }
      }
    }
    return transactionRepository.save(transaction);
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
  public Flux<Transaction> getTransactionsByAccountId(String id) {
    return transactionRepository.findAllByAccountId(id);
  }




}
