package com.amozh.operation.model.projection;

import com.amozh.operation.model.StockOperation;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

/**
 * Created by Andrii Mozharovskyi on 13.04.2016.
 */
@Projection(name = "operations_list", types = {StockOperation.class})
public interface OperationsListOperationProjection {
}
