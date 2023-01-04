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
 * This class represents the table rulename.
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
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  @Digits(integer = 4, fraction = 0)
  private Integer id;
  @Size(min = 1, max = 125)
  String name;
  @Size(min = 1, max = 125)
  String description;
  @Size(min = 1, max = 125)
  String json;
  @Size(min = 1, max = 512)
  String template;
  @Size(min = 1, max = 125)
  String sqlStr;
  @Size(min = 1, max = 125)
  String sqlPart;

  public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
    this.name = name;
    this.description = description;
    this.json = json;
    this.template = template;
    this.sqlStr = sqlStr;
    this.sqlPart = sqlPart;
  }
}
