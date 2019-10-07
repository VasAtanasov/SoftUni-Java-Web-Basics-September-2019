package app.web.rest;

import app.domain.models.binding.CarCreateBindingModel;
import app.domain.models.service.CarCreateServiceModel;
import app.domain.models.view.CarDetailsViewModel;
import app.service.CarService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;
import java.util.List;

@RequestScoped
@Path("/cars")
public class CarEndpoint {

    @Inject
    private CarService carService;

    @Inject
    private ModelMapper modelMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<CarDetailsViewModel> cars = carService.findAll();

        return Response
                .status(Status.OK)
                .entity(new GenericEntity<>(cars) {
                }).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCar(CarCreateBindingModel car, @Context UriInfo uriInfo) {
        String savedCarId = carService.create(this.modelMapper.map(car, CarCreateServiceModel.class));

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(savedCarId);

        return Response.created(builder.build()).build();
    }
}
