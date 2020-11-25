package com.imeira.account.transaction.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO implements Serializable {

  @JsonIgnore
  private Long id;

  @JsonProperty("account_id")
  private Long accountId;

  @JsonProperty("operation_type")
  private Long operationTypeId;

  private BigDecimal amount;

  @JsonProperty("event_date")
  private LocalDateTime eventDate;
}
