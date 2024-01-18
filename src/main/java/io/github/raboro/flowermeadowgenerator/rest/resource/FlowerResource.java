package io.github.raboro.flowermeadowgenerator.rest.resource;

import io.github.raboro.flowermeadowgenerator.businesslogic.FlowerBusinessLogic;
import io.github.raboro.flowermeadowgenerator.rest.dto.FlowerDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Raboro
 */
@Path("/flower")
public class FlowerResource {

    @Autowired
    private FlowerBusinessLogic logic;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlowerDTO> getAll() {
        return logic.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FlowerDTO addFlower(@RequestBody FlowerDTO flowerDTO) {
        return logic.addFlower(flowerDTO);
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteFlowerByID(@PathParam("id") long id) {
        logic.deleteFlower(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public FlowerDTO putFlower(@PathParam("id") long id, @RequestBody FlowerDTO flowerDTO) {
        return logic.putFlower(id, flowerDTO);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<FlowerDTO> getFlowerByID(@PathParam("id") long id) {
        FlowerDTO flower = logic.getFlowerByID(id);
        return flower == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(flower);
    }

    @GET
    @Path("/category/{category}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlowerDTO> getFlowersByCategory(@PathParam("category") String category) {
        return logic.getFlowersByCategory(category);
    }

    @GET
    @Path("/sort/{sorting}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlowerDTO> getSortedFlowers(@PathParam("sorting") String sorting) {
        return logic.sortFlowers(sorting);
    }

    @GET
    @Path("/sort/{sorting}/{reverse}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<FlowerDTO> getSortedFlowersReversed(@PathParam("sorting") String sorting,
                                                    @PathParam("reverse") boolean reverse) {
        return logic.sortFlowersReverse(sorting, reverse);
    }
}
