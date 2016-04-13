package com.amozh.operation.model.projection;

import com.amozh.item.model.projection.MinimalPreviewProjection;
import com.amozh.operation.model.StockOperationItem;
import org.springframework.data.rest.core.config.Projection;

import java.math.BigDecimal;

/**
 * Created by Andrii Mozharovskyi on 13.04.2016.
 */
@Projection(name = "operations_list", types = {StockOperationItem.class})
public interface OperationsListItemProjection {

    OperationsListOperationProjection getOperation();

    MinimalPreviewProjection getItem();

    BigDecimal getAmount();

    BigDecimal getPrice();

    BigDecimal getAmountAfter();

}
