package ru.kpfu.itis.servlets;

import ru.kpfu.itis.entities.Order;
import ru.kpfu.itis.services.OrderService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/orderhistory")
public class OrderHistoryServlet extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        ServletContext servletContext = config.getServletContext();
        orderService = (OrderService) servletContext.getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Order> orders = orderService.findOldOrders(request.getSession());
        request.setAttribute("orders", orders);
        request.getRequestDispatcher("/WEB-INF/views/orderHistory.jsp").forward(request, response);
    }
}
