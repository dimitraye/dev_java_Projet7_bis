package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trade")
public class Trade {
    // TODO: Map columns in data table TRADE with corresponding java fields

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  String account;
  String type;
  Double buyQuantity;
  Double sellQuantity;
  Double buyPrice;
  Double sellPrice;
  String benchmark;
  Timestamp tradeDate;
  String security;
  String status;
  String trader;
  String book;
  String creationName;
  Timestamp creationDate;
  String revisionName;
  Timestamp revisionDate;
  String dealName;
  String dealType;
  String sourceListId;
  String side;
}
