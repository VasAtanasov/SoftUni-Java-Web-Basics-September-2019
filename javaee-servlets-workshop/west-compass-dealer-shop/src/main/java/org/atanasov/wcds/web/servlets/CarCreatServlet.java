package org.atanasov.wcds.web.servlets;

import org.atanasov.wcds.domain.models.service.CarCreateServiceModel;
import org.atanasov.wcds.service.CarService;
import org.atanasov.wcds.util.ModelMapperWrapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.atanasov.wcds.constants.Constants.*;

@WebServlet(CARS_CREATE_URL)
public class CarCreatServlet extends HttpServlet {

    private final CarService carService;
    private final ModelMapperWrapper modelMapper;

    @Inject
    public CarCreatServlet(CarService carService, ModelMapperWrapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(CARS_CREATE_VIEW).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CarCreateServiceModel carCreateServiceModel = modelMapper.map(req, CarCreateServiceModel.class);
        carCreateServiceModel.setUser(req.getSession().getAttribute("userId").toString());

        try {
            carService.create(carCreateServiceModel);
        } catch (Exception ex) {
            req.setAttribute(ERR_MSG_ATTR, "Something went wrong with upload please try again");
            req.getRequestDispatcher(CARS_CREATE_URL);
            return;
        }
        resp.sendRedirect(CARS_ALL_URL);
    }
}
