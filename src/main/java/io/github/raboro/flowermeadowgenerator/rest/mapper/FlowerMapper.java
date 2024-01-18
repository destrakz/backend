package io.github.raboro.flowermeadowgenerator.rest.mapper;

import io.github.raboro.flowermeadowgenerator.database.model.Flower;
import io.github.raboro.flowermeadowgenerator.rest.dto.FlowerDTO;
import org.springframework.stereotype.Component;

/**
 * @author Raboro
 */
@Component
public class FlowerMapper {

    public Flower toModel(FlowerDTO dto) {
        return new Flower(dto.getName(), dto.getCategory(), dto.getStemColor(), dto.getStemHeight(), dto.getStemWidth(),
                dto.isStemThrones(), dto.getPetalColor(), dto.getPetalHeight(), dto.getPetalWidth(), dto.isPetalThrones());
    }

    public FlowerDTO toDTO(Flower model) {
        return new FlowerDTO(model.getId(), model.getName(), model.getCategory(), model.getStemColor(), model.getStemHeight(), model.getStemWidth(),
                model.isStemThrones(), model.getPetalColor(), model.getPetalHeight(), model.getPetalWidth(), model.isPetalThrones());
    }
}
