package com.amozh.operation;

import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.impl.InOperation;
import com.amozh.supplier.Supplier;
import com.amozh.supplier.SupplierRepository;
import com.amozh.supplier.SupplierService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Andrii Mozharovskyi on 21.04.2016.
 */
@Component
@RepositoryEventHandler(StockOperation.class)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockOperationEventHandler {

    private final @NonNull
    SupplierRepository supplierRepository;

    private final @NonNull
    SupplierService supplierService;

    @HandleBeforeCreate
    public void mapOperationItemsToOperation(StockOperation operation) {
        operation.getItems().stream().forEach(item -> item.setOperation(operation));
        supplierService.saveIfPresentAndNotExists(operation);
    }

}
