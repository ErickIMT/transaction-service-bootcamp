package com.nttdata.transactionservice.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document("transactions")
public class Transaction {

    @Id
    private String id;
    private String accountId;
    private String typeTransaction;
    private String commerce;
    private float amount;
    @DateTimeFormat(pattern = "yyyy/MMMM/dd HH:mm:ss")
    private Date date;
}
