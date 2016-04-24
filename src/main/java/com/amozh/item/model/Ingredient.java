package com.amozh.item.model;

import com.amozh.category.Category;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Andrii Mozharovskyi on 05.04.2016.
 */
@Entity @DiscriminatorValue("2")
@Data
public class Ingredient extends Item {

    @Size(max = 10)
    @Column(length = 10)
    private String sku;

    @NotNull
    @Size(max = 10)
    @Column(length = 10)
    private String units;

    @Override
    @RestResource(rel = "ingredientCategory")
    public Category getCategory(){
        return super.getCategory();
    }
}
