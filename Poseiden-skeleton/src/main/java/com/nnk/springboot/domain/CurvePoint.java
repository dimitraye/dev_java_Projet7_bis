package com.nnk.springboot.domain;

import javax.validation.constraints.Digits;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


/**
 * This class represents the table curvePoint.
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
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Digits(integer = 4, fraction = 0)
    private Integer id;

    @NotNull(message = "must not be null")
    Integer curveId;
    Timestamp asOfDate;
    Double term;
    Double value;
    Timestamp creationDate;

    public CurvePoint(int curveId, double term, double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }
}
