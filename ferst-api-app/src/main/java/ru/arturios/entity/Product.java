package ru.arturios.entity;

import javax.persistence.*;

@Entity
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "findAll", query = "from Product"),
        @NamedQuery(name = "countAll", query = "select count(*) from Product"),
        @NamedQuery(name = "delete", query = "delete from Product p where p.id = :id")
})

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column(length = 1000)
    private String description;

    @Column(scale = 2, precision = 10)
    private int price;

    public Product() {

    }

    public Product(Long id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
