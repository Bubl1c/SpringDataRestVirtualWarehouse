package com.amozh.operation;

import com.amozh.Api;
import com.amozh.operation.model.StockOperation;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.hateoas.RelProvider;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii Mozharovskyi on 15.04.2016.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class StockOperationRelProvider implements RelProvider {
    @Override
    public boolean supports(Class<?> arg0) {
        return org.apache.commons.lang3.ClassUtils.isAssignable(arg0, StockOperation.class);
    }
    @Override
    public String getItemResourceRelFor(Class<?> type) {
        return Api.RES_SINGLE_STOCK_OPERATION;
    }
    @Override
    public String getCollectionResourceRelFor(Class<?> type) {
        return Api.RES_COLLECTION_STOCK_OPERATIONS;
    }
}
