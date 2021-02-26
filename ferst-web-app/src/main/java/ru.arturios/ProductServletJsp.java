package ru.arturios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductServletJsp", urlPatterns = "/products_jsp")
public class ProductServletJsp extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductServletJsp.class);

    private ProductRepository repository = new ProductRepository();

    @Override
    public void init() throws ServletException {
        logger.info("Servlet init {}", getClass().getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products", repository.getAll());

        getServletContext().getRequestDispatcher("/WEB-INF/views/products.jsp")
                .forward(req, resp);
    }
}
