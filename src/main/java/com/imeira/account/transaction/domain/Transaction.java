package com.imeira.account.transaction.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public final class Transaction implements Serializable {

  @Id
  private Long id;

  @DBRef
  private Account account;

  @DBRef
  private OperationType operationType;

  private BigDecimal amount;

  private LocalDateTime eventDate;

}
