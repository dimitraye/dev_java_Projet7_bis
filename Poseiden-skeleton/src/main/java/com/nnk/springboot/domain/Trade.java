package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * This class represents the entity trade.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade")
public class Trade {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Digits(integer = 4, fraction = 0)
  private Integer tradeId;
  @Size(min = 1, max = 30)
  @NotBlank(message = "account is mandatory")
  String account;
  @Size(min = 1, max = 30)
  @NotBlank(message = "type is mandatory")
  String type;
  Double buyQuantity;
  Double sellQuantity;
  Double buyPrice;
  Double sellPrice;
  @Size(min = 1, max = 125)
  String benchmark;
  Timestamp tradeDate;
  @Size(min = 1, max = 125)
  String security;
  @Size(min = 1, max = 10)
  String status;
  @Size(min = 1, max = 125)
  String trader;
  @Size(min = 1, max = 125)
  String book;
  @Size(min = 1, max = 125)
  String creationName;
  Timestamp creationDate;
  @Size(min = 1, max = 125)
  String revisionName;
  Timestamp revisionDate;
  @Size(min = 1, max = 125)
  String dealName;
  @Size(min = 1, max = 125)
  String dealType;
  @Size(min = 1, max = 125)
  String sourceListId;
  @Size(min = 1, max = 125)
  String side;

  public Trade(String account, String type) {
    this.account = account;
    this.type = type;
  }
}
