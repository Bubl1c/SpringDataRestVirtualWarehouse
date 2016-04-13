package com.amozh.category.dto;

import com.amozh.category.CategoryContentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Andrii Mozharovskyi on 13.04.2016.
 */
@Data
@NoArgsConstructor
public class CategoryTreeDTO {
    private long id;
    @JsonProperty("label")
    private String name;
    private CategoryContentType contentType;
    private long parentId;
    private Collection<CategoryTreeItemDTO> items = new ArrayList<>();

    public CategoryTreeDTO(long id, String name, CategoryContentType contentType, long parentId) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
        this.parentId = parentId;
    }

    public void addItem(CategoryTreeItemDTO item) {
        items.add(item);
    }
}
