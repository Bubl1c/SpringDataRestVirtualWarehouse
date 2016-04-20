package com.amozh.operation.dto.projection;

import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.StockOperationType;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Andrii Mozharovskyi on 13.04.2016.
 */
@Projection(name = "def", types = {StockOperation.class})
public interface StockOperationProjection {
    StockOperationType getType();
}
