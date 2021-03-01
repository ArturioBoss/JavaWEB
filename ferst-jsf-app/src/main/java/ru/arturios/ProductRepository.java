package ru.arturios;

import ru.arturios.entity.Product;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@ApplicationScoped
public class ProductRepository {

    private AtomicInteger sequence = new AtomicInteger();

    private Map<String, Product> productMap = Collections.synchronizedMap(new LinkedHashMap<>());
    private Map<String, Product> listMap = Collections.synchronizedMap(new LinkedHashMap<>());
    private ArrayList<String> productBasket = new ArrayList<>();



    public ProductRepository() {
        this.merge(new Product("1", "Книги", "Инструкции по сборке", 50));
        this.merge(new Product("2", "Мышь","Мышь Logitech", 150));
        this.merge(new Product("3", "Клавиатура","Клавиатура Logitech", 200));
        this.merge(new Product("4", "Компьютер","Процессор: Intel(R) Core(TM) i7-9700 CPU @ 3.00GHz, 3000 МГц, ядер: 8", 500));
        sequence.set(productMap.size());
    }

    public Collection<Product> getAll() {
        return productMap.values();
    }

    public Collection<Product> getBasketAll() {
        if (productBasket.size()>0) {
            return listMap.values();
        }
        return null;
    }

    public Product getById(String id) {
        return productMap.get(id);
    }

    public void merge(Product product) {
        if (product.getId() == null || !productMap.containsKey(product.getId())) {
            product.setId(String.valueOf(sequence.incrementAndGet()));
        }
        productMap.put(product.getId(), product);
    }

    public void addBasket(Product product) {
        productBasket.add(product.getId());
        listMap.put(product.getId(),product);
    }

    public int qvant() {
        return productBasket.size();
    }

    public void delete(Product product) {
        productMap.remove(product.getId());
    }

    public void deleteBasket() {
        listMap.clear();
        productBasket.clear();
    }

    public String totalBasket() {
        return "0";
    }
}
