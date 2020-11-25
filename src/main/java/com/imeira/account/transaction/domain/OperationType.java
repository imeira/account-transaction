package com.imeira.account.transaction.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OperationType implements Serializable {

  @Id
  private Long id;

  private String description;

  private boolean negative = false;

}
