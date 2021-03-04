package ru.arturios;

import ru.arturios.entity.Product;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Named
@ApplicationScoped
public class ProductRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction userTransaction;

    private AtomicInteger sequence = new AtomicInteger();

    private Map<String, Product> productMap = Collections.synchronizedMap(new LinkedHashMap<>());
    private Map<Long, Product> listMap = Collections.synchronizedMap(new LinkedHashMap<>());
    private ArrayList<Long> productBasket = new ArrayList<>();


    @PostConstruct
    public void ProductRepository() throws Exception {

        if (qvantAll() == 0) {
            try {
                userTransaction.begin();
                this.merge(new Product(1L, "Книги", "Инструкции по сборке", 50));
                this.merge(new Product(2L, "Мышь", "Мышь Logitech", 150));
                this.merge(new Product(3L, "Клавиатура", "Клавиатура Logitech", 200));
                this.merge(new Product(4L, "Компьютер", "Процессор: Intel(R) Core(TM) i7-9700 CPU @ 3.00GHz, 3000 МГц, ядер: 8", 500));
                sequence.set(productMap.size());
                userTransaction.commit();
            } catch (Exception e){
                userTransaction.rollback();
            }
        }
    }

    public Collection<Product> getAll() {
        return em.createNamedQuery("findAll", Product.class)
                .getResultList();
    }

    public Collection<Product> getBasketAll() {
        if (productBasket.size()>0) {
            return listMap.values();
        }
        return null;
    }

    public Product getById(Long id) {
        return productMap.get(id);
    }

    @Transactional
    public void merge(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    public void addBasket(Product product) {
        productBasket.add(product.getId());
        listMap.put(product.getId(),product);
    }

    public int qvant() {
        return productBasket.size();
    }

    public Long qvantAll(){
        return em.createNamedQuery("countAll", Long.class)
                .getSingleResult();
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
