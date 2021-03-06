package ru.arturios;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.arturios.entity.Product;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.Collection;

@ManagedBean(name = "products")
@SessionScoped
public class ProductsBean {

    private static final Logger logger = LoggerFactory.getLogger(ProductsBean.class);

    private Product product;

    @Inject
    private ProductRepository productRepository;

    public Long getId() {
        return product.getId();
    }

    public String goBasket(Product productr) {
        productRepository.addBasket(productr);
        return "/index.xhtml?faces-redirect=true";
    }

    public void setId(Long id) {
        product.setId(id);
    }

    public String getName() {
        return product.getName();
    }

    public void setName(String name) {
        product.setName(name);
    }

    public int getPrice() {
        return product.getPrice();
    }

    public void setPrice(int price) {
        product.setPrice(price);
    }

    public Collection<Product> getProductList() {
        return productRepository.getAll();
    }

    public Collection<Product> getBasketList() {
        return productRepository.getBasketAll();
    }

    public String getPriceB(){

        return productRepository.totalBasket();
    }


    public void deleteAction(Product product) {
        productRepository.delete(product);
    }

    public String addAction() {
        product = new Product();
        return "/product.xhtml?faces-redirect=true";
    }

    public String editAction(Product product) {
        this.product = product;
        return "/product.xhtml?faces-redirect=true";
    }

    public String saveProduct() {
        productRepository.merge(product);
        return "/index.xhtml?faces-redirect=true";
    }

    public String getQvants() {
        if (productRepository.qvant()>0){
            return "В корзине "+productRepository.qvant();
        }
        return "Корзина";
    }

    public void delBasket() {
        productRepository.deleteBasket();
    }

}
