package com.imeira.account.transaction.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OperationTypeDTO implements Serializable {

  private BigInteger id;

  private String description;

  private boolean negative = false;

}
