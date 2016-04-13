package com.amozh.item.model;

import com.amozh.category.Category;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

/**
 * Created by Andrii Mozharovskyi on 05.04.2016.
 */
@Entity @DiscriminatorValue("Ingredient")
@Data
public class Ingredient extends Item {

    @Column(length = 10)
    private String sku;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit units;

    @Override
    @RestResource(rel = "ingredientCategory")
    public Category getCategory(){
        return super.getCategory();
    }
}
