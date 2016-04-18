package com.amozh.operation.model.impl;

import com.amozh.Api;
import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.StockOperationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Andrii Mozharovskyi on 12.04.2016.
 */
@Entity
@Table(name = StockOperationType.HOLD_OPERATION_NAME + "_operation")
@Data
@NoArgsConstructor
@RestResource(rel = StockOperationType.HOLD_OPERATION_NAME, path = Api.RES_COLLECTION_STOCK_OPERATIONS)
public class HoldOperation extends StockOperation {

    @Override
    public StockOperationType getType() {
        return StockOperationType.HOLD;
    }
}
