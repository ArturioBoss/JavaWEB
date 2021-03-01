package ru.arturios;

import ru.arturios.entity.Product;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductRepository {

    private AtomicInteger sequence = new AtomicInteger();

    private Map<String, Product> productMap = Collections.synchronizedMap(new LinkedHashMap<>());

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

    public Product getById(String id) {
        return productMap.get(id);
    }

    public void merge(Product product) {
        if (product.getId() == null || !productMap.containsKey(product.getId())) {
            product.setId(String.valueOf(sequence.incrementAndGet()));
        }
        productMap.put(product.getId(), product);
    }

    public void delete(Product product) {
        productMap.remove(product.getId());
    }
}
