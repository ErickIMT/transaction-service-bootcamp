package com.nttdata.transactionservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {

    private String id;
    private String account;
    private float amount;
}
