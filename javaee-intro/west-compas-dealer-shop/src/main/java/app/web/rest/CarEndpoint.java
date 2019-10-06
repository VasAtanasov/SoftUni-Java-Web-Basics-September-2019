package app.web.rest;

import app.domain.entities.Car;
import app.repository.CarRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import java.util.List;

/**
 * @author Vasil Atanasov
 */
@RequestScoped
@Path("cars")
public class CarEndpoint {

    @Inject
    private CarRepository carRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<Car> cars = carRepository.findAll();

        return Response
                .status(Status.OK)
                .entity(new GenericEntity<>(cars) {
                }).build();
    }

    @GET
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodo(@PathParam("id") String id) {
        Car car = carRepository.find(id);
        return Response.ok(car).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTodo(@PathParam("id") String id, Car car) {
        Car updateCar = carRepository.find(id);

        updateCar.setModel(car.getModel());
        updateCar.setBrand(car.getBrand());
        updateCar.setYear(car.getYear());
        updateCar.setEngine(car.getEngine());

        carRepository.merge(updateCar);

        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createTodo(Car car, @Context UriInfo uriInfo) {
        Car savedCar = carRepository.save(car);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(savedCar.getId());

        return Response.created(builder.build()).build();
    }

    @DELETE
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTodo(@PathParam("id") String  id) {
        Car car = carRepository.find(id);
        carRepository.remove(car);
        return Response.ok().build();
    }
}
