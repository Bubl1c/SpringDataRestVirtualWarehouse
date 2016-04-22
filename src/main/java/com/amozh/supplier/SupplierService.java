package com.amozh.supplier;

import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.impl.InOperation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Andrii Mozharovskyi on 21.04.2016.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SupplierService {

    private final @NonNull SupplierRepository supplierRepository;

    @Async
    public void saveIfPresentAndNotExists(StockOperation stockOperation) {
        if(stockOperation instanceof InOperation) {
            InOperation inOperation = (InOperation) stockOperation;
            try {
                supplierRepository.save(new Supplier(inOperation.getSupplier()));
            } catch (DataIntegrityViolationException exception) {
                // Supplier with such name already exists!
                // We assume this exception is OK here. No need logging or handling.
            }
        }
    }
}
