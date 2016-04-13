package com.amozh.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Created by Andrii Mozharovskyi on 13.04.2016.
 */
@Data
@NoArgsConstructor
public class CategoryTreeItemDTO {
    private long id;
    @JsonProperty("label")
    private String name;
    private String units;
    private BigDecimal stock;
    private BigDecimal hold;

    public CategoryTreeItemDTO(long id, String name, BigDecimal stock, BigDecimal hold) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.hold = hold;
    }
}
