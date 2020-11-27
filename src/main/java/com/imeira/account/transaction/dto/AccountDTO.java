package com.imeira.account.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO implements Serializable {

  private BigInteger id;


  @JsonProperty("document_number")

  private String documentNumber;

  @JsonProperty("available_credit_limit")
  private BigDecimal availableCreditLimit;


}
