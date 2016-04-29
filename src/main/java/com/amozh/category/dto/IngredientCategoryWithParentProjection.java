package com.amozh.category.dto;

import com.amozh.category.Category;
import com.amozh.category.CategoryContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Andrii Mozharovskyi on 28.04.2016.
 */
@Projection(name = "i-with-parent", types = Category.class)
public interface IngredientCategoryWithParentProjection {

    Long getId();

    String getname();

    String getDescription();

    @Value("#{target.parent?.id}")
    Long getParentId();

    CategoryContentType getContentType();

}
