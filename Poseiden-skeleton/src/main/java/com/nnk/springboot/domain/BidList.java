package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Timestamp;


/**
 * This class represents the entity bidlist.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bidlist")
public class BidList {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Digits(integer = 4, fraction = 0)
  private Integer bidListId;

  @Size(min = 1, max = 30)
  @NotBlank(message = "Username is mandatory")
  private String account;

  @Size(min = 1, max = 30)
  @NotBlank(message = "Username is mandatory")
  private String type;

  private Double bidQuantity;

  private Double askQuantity;

  private Double bid;

  private Double ask;

  @Size(min = 1, max = 125)
  private String benchmark;

  private Timestamp bidListDate;

  private String commentary;

  @Size(min = 1, max = 125)
  private String security;

  @Size(min = 1, max = 10)
  private String status;

  @Size(min = 1, max = 125)
  private String trader;

  @Size(min = 1, max = 125)
  private String book;

  @Size(min = 1, max = 125)
  private String creationName;

  private Timestamp creationDate;

  @Size(min = 1, max = 125)
  private String revisionName;

  private Timestamp revisionDate;

  @Size(min = 1, max = 125)
  private String dealName;

  @Size(min = 1, max = 125)
  private String dealType;

  @Size(min = 1, max = 125)
  private String sourceListId;

  @Size(min = 1, max = 125)
  private String side;

  public BidList(String account, String type, double bidQuantity) {
    this.account = account;
    this.type = type;
    this.bidQuantity = bidQuantity;
  }
}
