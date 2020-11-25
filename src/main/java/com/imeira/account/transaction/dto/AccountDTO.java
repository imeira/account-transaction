package com.imeira.account.transaction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDTO implements Serializable {

  private Long id;

  @JsonProperty("document_number")
  private String documentNumber;


}
