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
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Integer id;
  String name;
  String description;
  String json;
  String template;
  String sqlStr;
  String sqlPart;
}
