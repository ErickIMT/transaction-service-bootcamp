package com.nttdata.transactionservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditDTO {

    private String id;
    private String creditAccount;
    private float amount;
    private float limit;
}
