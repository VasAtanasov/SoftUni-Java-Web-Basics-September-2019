package app.web.servlets;

import app.domain.enums.Engine;
import app.domain.models.binding.CarCreateBindingModel;
import app.domain.models.service.CarCreateServiceModel;
import app.service.CarService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CarCreatServlet extends HttpServlet {

    private final CarService carService;
    private final ModelMapper modelMapper;

    @Inject
    public CarCreatServlet(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/create.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CarCreateBindingModel model = CarCreateBindingModel.builder()
                .model(req.getParameter("model"))
                .brand(req.getParameter("brand"))
                .year(Integer.parseInt(req.getParameter("year")))
                .engine(Engine.fromString(req.getParameter("engine")))
                .build();

        this.carService.create(this.modelMapper.map(model, CarCreateServiceModel.class));
        resp.sendRedirect("/all");
    }
}
