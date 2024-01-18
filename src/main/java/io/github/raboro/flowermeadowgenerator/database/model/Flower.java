package io.github.raboro.flowermeadowgenerator.database.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Raboro
 */
@Entity
@Data
@NoArgsConstructor
public class Flower implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long id;
    private String name;
    private String category;
    private String stemColor;
    private short stemHeight;
    private short stemWidth;
    private boolean stemThrones;
    private String petalColor;
    private short petalHeight;
    private short petalWidth;
    private boolean petalThrones;

    public Flower(String name, String category, String stemColor, short stemHeight, short stemWidth, boolean stemThrones, String petalColor, short petalHeight, short petalWidth, boolean petalThrones) {
        this.name = name;
        this.category = category;
        this.stemColor = stemColor;
        this.stemHeight = stemHeight;
        this.stemWidth = stemWidth;
        this.stemThrones = stemThrones;
        this.petalColor = petalColor;
        this.petalHeight = petalHeight;
        this.petalWidth = petalWidth;
        this.petalThrones = petalThrones;
    }
}
