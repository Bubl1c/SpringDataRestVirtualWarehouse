package com.amozh.operation.model.impl;

import com.amozh.Api;
import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.StockOperationType;
import com.amozh.storage.Storage;
import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;

/**
 * Created by Andrii Mozharovskyi on 12.04.2016.
 */
//@Entity
//@Table(name = "move_operation")
@Data
@RestResource(rel = StockOperationType.MOVE_OPERATION_NAME, path = Api.RES_COLLECTION_STOCK_OPERATIONS)
public class MoveOperation extends StockOperation {

    @ManyToOne
    @JoinColumn(name = "from_storage_id")
    private Storage from;

    @ManyToOne
    @JoinColumn(name = "to_storage_id")
    private Storage to;

    @Override
    public StockOperationType getType() {
        return StockOperationType.MOVE;
    }
}
