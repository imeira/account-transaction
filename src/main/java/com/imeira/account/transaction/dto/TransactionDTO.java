package com.imeira.account.transaction.dto;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO implements Serializable {

//  @JsonIgnore
  private BigInteger id;

  @JsonProperty("account_id")
  private BigInteger accountId;

  @JsonProperty("operation_type")
  private BigInteger operationTypeId;

  private BigDecimal amount;

  @JsonProperty("event_date")
  private LocalDateTime eventDate;
}
