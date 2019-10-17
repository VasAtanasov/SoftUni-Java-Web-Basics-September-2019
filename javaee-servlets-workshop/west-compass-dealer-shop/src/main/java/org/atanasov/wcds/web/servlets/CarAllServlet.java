package org.atanasov.wcds.web.servlets;

import org.atanasov.wcds.domain.models.service.CarServiceModel;
import org.atanasov.wcds.service.CarService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.atanasov.wcds.constants.Constants.CARS_ALL_URL;
import static org.atanasov.wcds.constants.Constants.CARS_ALL_VIEW;

@WebServlet(CARS_ALL_URL)
public class CarAllServlet extends HttpServlet {

    private final CarService carService;

    @Inject
    public CarAllServlet(CarService carService) {
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarServiceModel> cars = carService.findAll();
        req.setAttribute("cars", cars);
        req.getRequestDispatcher(CARS_ALL_VIEW).forward(req, resp);
    }
}
