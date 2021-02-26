package ru.arturios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.arturios.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProductServletJstl", urlPatterns = "/products_jstl")
public class ProductServletJstl extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductServletJstl.class);

    private ProductRepository repository = new ProductRepository();

    @Override
    public void init() throws ServletException {
        logger.info("Инициализация Servlet {}", getClass().getSimpleName());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String command = req.getParameter("command");
        if (command != null) {
            switch (command) {
                case "NEW": {
                    req.setAttribute("product", new Product());
                    getServletContext().getRequestDispatcher("/WEB-INF/views/product.jsp")
                            .forward(req, resp);
                }
                case "EDIT": {
                    Product product = getProductWithCheck(req.getParameter("id"));
                    req.setAttribute("product", product);
                    getServletContext().getRequestDispatcher("/WEB-INF/views/product.jsp")
                            .forward(req, resp);
                    return;
                }
                case "DELETE": {
                    Product product = getProductWithCheck(req.getParameter("id"));
                    repository.delete(product);

                    req.setAttribute("products", repository.getAll());
                    resp.sendRedirect("products_jstl");
                    return;
                }
            }
        }

        req.setAttribute("products", repository.getAll());

        getServletContext().getRequestDispatcher("/WEB-INF/views/products-jstl.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Product product = id.equals("-1") ? new Product() : getProductWithCheck(id);
        product.setName(req.getParameter("name"));
        product.setDescription(req.getParameter("description"));
        product.setPrice(Integer.valueOf(req.getParameter("price")));
        repository.merge(product);

        resp.sendRedirect("products_jstl");
    }

    private Product getProductWithCheck(String id) {
        if (id == null || id.isEmpty()) {
            logger.error("Товар равен 0.");
            throw new IllegalStateException("Товар равен 0");
        }
        Product product = repository.getById(id);
        if (product == null) {
            logger.error("Товар с идентификатором {} не найден", id);
            throw new IllegalStateException("Товар не найден");
        }
        return product;
    }
}