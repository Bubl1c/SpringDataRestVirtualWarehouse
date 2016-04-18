package com.amozh.operation.model.projection;

import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.StockOperationType;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Andrii Mozharovskyi on 13.04.2016.
 */
@Projection(name = "operations_list", types = {StockOperation.class})
public interface OperationsListOperationProjection {
    StockOperationType getType();
    Date getDateTimeCreated();
    Date getDateTimePerformed();
}
