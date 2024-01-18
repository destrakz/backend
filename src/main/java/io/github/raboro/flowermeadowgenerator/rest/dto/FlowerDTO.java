package io.github.raboro.flowermeadowgenerator.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Raboro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowerDTO {

    long id;
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
}
