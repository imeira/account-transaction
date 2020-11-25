package com.imeira.account.transaction.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OperationTypeDTO implements Serializable {

  @JsonIgnore
  private Long id;

  private String description;

  private boolean negative = false;

}
