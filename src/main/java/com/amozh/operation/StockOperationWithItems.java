package com.amozh.operation;

import com.amozh.operation.StockOperation;
import com.amozh.operation.StockOperationItem;
import com.amozh.operation.StockOperationType;
import org.springframework.data.rest.core.config.Projection;

import java.util.List;

/**
 * Created by Andrii Mozharovskyi on 08.04.2016.
 */
@Projection(name = "with_items", types = {StockOperation.class})
public interface StockOperationWithItems {

    StockOperationType getType();

    List<StockOperationItem> getItems();
}
