package com.amozh.operation.model.impl;

import com.amozh.Api;
import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.StockOperationType;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Created by Andrii Mozharovskyi on 12.04.2016.
 */
@Entity
@DiscriminatorValue(StockOperationType.IN_OPERATION_NAME)
@Data
@RestResource(rel = StockOperationType.IN_OPERATION_NAME, path = Api.RES_COLLECTION_STOCK_OPERATIONS)
public class InOperation extends StockOperation {

    @Column(length = 50)
    @Size(max=5)
    private String supplier;

    @Override
    public StockOperationType getType() {
        return StockOperationType.IN;
    }
}
