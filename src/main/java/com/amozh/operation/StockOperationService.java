package com.amozh.operation;

import com.amozh.exception.InvalidOperationTypeException;
import com.amozh.item.ItemRepository;
import com.amozh.item.model.Item;
import com.amozh.operation.dto.HoldOperationDTO;
import com.amozh.operation.dto.InOperationDTO;
import com.amozh.operation.dto.StockOperationDTO;
import com.amozh.operation.dto.StockOperationItemDTO;
import com.amozh.operation.item.StockOperationItem;
import com.amozh.operation.item.StockOperationItemRepository;
import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.StockOperationType;
import com.amozh.operation.model.impl.HoldOperation;
import com.amozh.operation.model.impl.InOperation;
import com.amozh.storage.Storage;
import com.amozh.storage.StorageRepository;
import com.sun.javaws.exceptions.InvalidArgumentException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.lang.model.UnknownEntityException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.UnknownTypeException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * Created by Andrii Mozharovskyi on 18.04.2016.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockOperationService {

    private final @NonNull
    StockOperationRepository stockOperationRepository;

    private final
    @NonNull
    StockOperationItemRepository stockOperationItemRepository;

    private final @NonNull
    StockOperationMapper stockOperationMapper;

    Page<StockOperationItem> searchItems(@NotNull Long storageId,
                                         Long itemId,
                                         Date fromDate,
                                         Date toDate,
                                         StockOperationType[] operationTypes,
                                         Pageable pageable) {
        if(operationTypes == null) {
            operationTypes = StockOperationType.values();
        }
        Collection<Class<? extends StockOperation>> operationClasses = new ArrayList<>();
        for(StockOperationType type : operationTypes) {
            operationClasses.add(type.getClazz());
        }
        return stockOperationItemRepository.searchAllWithOperationAndItem(
                storageId, itemId, fromDate, toDate,
                operationClasses,
                pageable);
    }

    public StockOperation save(StockOperationDTO stockOperationDTO) {
        StockOperation operation = stockOperationMapper.toEntity(stockOperationDTO);
        for(StockOperationItem item : operation.getItems()) {
            item.setOperation(operation);
        }
        stockOperationRepository.save(operation);
        return operation;
    }
}
