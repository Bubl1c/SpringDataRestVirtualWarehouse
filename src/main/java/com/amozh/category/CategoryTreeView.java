package com.amozh.category;

import com.amozh.category.Category;
import com.amozh.category.CategoryContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

/**
 * Created by Andrii Mozharovskyi on 08.04.2016.
 */
@Projection(name = "tree", types = { Category.class })
public interface CategoryTreeView {

    long getId();

    String getName();

    CategoryContentType getContentType();

    BigDecimal getStock();

    BigDecimal getHoldStock();

    @Value("#{target.parent != null ? target.parent.id : null}")
    String getParent();

}
