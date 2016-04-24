package com.amozh.item.model;

import com.amozh.category.Category;
import com.amozh.item.model.Ingredient;
import com.amozh.item.model.Item;
import lombok.Data;
import org.hibernate.annotations.Where;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Developer on 18.09.2015.
 */
@Entity @DiscriminatorValue("1")
@Data
public class Product extends Item {

    @Column(columnDefinition = "int default 0", nullable = false)
    private int bonusPoints;

    private String pictureUrl;

    private double position;

    @ManyToMany
    @JoinTable(name = "mb_ProductIngredients",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    @Where(clause = "deleted = 0")
    private List<Ingredient> ingredients;

    @Override
    @RestResource(rel = "productCategory")
    public Category getCategory(){
        return getCategory();
    }

}

