package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;


/**
 * This class represents the entity rulename.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rulename")
public class RuleName {

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
