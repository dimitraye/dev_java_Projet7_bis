package com.nnk.springboot.domain;

import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * This class represents the table bidlist.
 */
//TODO : Ajouter des commentaires pour les annotations
//Permet d'utiliser différentes méthodes tel que toString(), getters, setters...
@Data
//Annotation permettant de créer des constructeurs sans arguments
@NoArgsConstructor
//Annotation permettant de créer des constructeurs avec arguments
@AllArgsConstructor
//Indique que cette classe est une entité (une entité représente un table dans la BD)
@Entity
//Annotation Indiquant que la table dans la BD prendra le nom en paramêtre
@Table(name = "bidlist")
public class BidList {
    // TODO: Map columns in data table BIDLIST with corresponding java fields

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  //Annotation qui spécifie les contraintes de validation pour un champ numérique.
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

  //TODO : voir commentary, commentary n'existe âs dans le fichier SQL alors qu'il est présent dans le pdf  "FINACIAL_ENTITIES_JPO"
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
