package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * This class represents the table rating.
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
@Table(name = "rating")
public class Rating {
    // TODO: Map columns in data table RATING with corresponding java fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Digits(integer = 4, fraction = 0)
    private Integer id;

    @Size(min = 1, max = 125)
    String moodysRating;

    @Size(min = 1, max = 125)
    String sandPRating;

    @Size(min = 1, max = 125)
    String fitchRating;

    //TODO : faire des recherches sur "tiny int"
    @Max(value = 127)
    Integer orderNumber;

    public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
        this.moodysRating = moodysRating;
        this.sandPRating = sandPRating;
        this.fitchRating = fitchRating;
        this.orderNumber = orderNumber;
    }
}
