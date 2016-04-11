package com.amozh.item;

import com.amozh.item.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Andrii Mozharovskyi on 08.04.2016.
 */
@Projection(name = "tree", types = { Item.class })
public interface ItemInCategoryTreeView {

    long getId();

    String getName();

    @Value("#{target.unit}")
    String getUnits();

}
