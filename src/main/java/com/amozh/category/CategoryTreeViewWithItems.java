package com.amozh.category;

import com.amozh.item.ItemInCategoryTreeView;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * Created by Andrii Mozharovskyi on 08.04.2016.
 */
@Projection(name = "tree_with_items", types = { Category.class })
public interface CategoryTreeViewWithItems extends CategoryTreeView {

    List<ItemInCategoryTreeView> getItems();

}
