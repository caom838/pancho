package com.s2c.android.asea.Model;

/**
 * Created by caom on 20/09/2015.
 */
public class Plan {

    private Long id;
    private String name;
    private String description;
    private Double price;

    public Plan() {
    }

    public Plan(Long id, String name,String description, Double price) {
        this.id = id;
        this.name = name;
        this.description =  description;
        this.price = price;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
