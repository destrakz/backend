package io.github.raboro.flowermeadowgenerator.businesslogic;

import io.github.raboro.flowermeadowgenerator.database.model.Flower;
import io.github.raboro.flowermeadowgenerator.database.repository.FlowerRepository;
import io.github.raboro.flowermeadowgenerator.rest.dto.FlowerDTO;
import io.github.raboro.flowermeadowgenerator.rest.mapper.FlowerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author Raboro
 */
@Service
public class FlowerBusinessLogic {

    @Autowired
    private FlowerRepository repository;
    private final FlowerMapper mapper = new FlowerMapper();

    public List<FlowerDTO> getAll() {
        return repository.findAll().parallelStream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public FlowerDTO addFlower(FlowerDTO flowerDTO) {
        Flower flower = mapper.toModel(flowerDTO);
        return mapper.toDTO(repository.save(flower));
    }

    public void deleteFlower(long id) {
        repository.deleteById(id);
    }

    public FlowerDTO putFlower(long id, FlowerDTO flowerDTO) {
        Optional<Flower> byId = repository.findById(id);
        if (byId.isEmpty()) {
            return mapper.toDTO(repository.save(mapper.toModel(flowerDTO)));
        }
        return putFlowerValid(flowerDTO, byId.get());
    }

    private FlowerDTO putFlowerValid(FlowerDTO flowerDTO, Flower byId) {
        Flower flower = mapper.toModel(flowerDTO);
        flower.setId(byId.getId());
        return mapper.toDTO(repository.save(flower));
    }

    public FlowerDTO getFlowerByID(long id) {
        Optional<Flower> byId = repository.findById(id);
        return byId.map(mapper::toDTO).orElse(null);
    }

    public List<FlowerDTO> getFlowersByCategory(String category) {
        return repository.findAllByCategory(category).parallelStream()
                .map(mapper::toDTO)
                .toList();
    }

    public List<FlowerDTO> sortFlowers(String name) {
        final Optional<FlowerSorting> sorting = FlowerSorting.getSorting(name);
        return sorting.isPresent() ? sortBy(sorting.get().sort()) : getAll();
    }

    private List<FlowerDTO> sortBy(Consumer<List<FlowerDTO>> sorter) {
        List<FlowerDTO> flowers = getAll();
        sorter.accept(flowers);
        return flowers;
    }

    public List<FlowerDTO> sortFlowersReverse(String name, boolean reverse) {
        List<FlowerDTO> flowers = sortFlowers(name);
        if (reverse) {
            Collections.reverse(flowers);
        }
        return flowers;
    }
}
