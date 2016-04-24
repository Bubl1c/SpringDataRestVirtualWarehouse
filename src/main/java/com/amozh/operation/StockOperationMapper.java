package com.amozh.operation;

import com.amozh.exception.InvalidOperationTypeException;
import com.amozh.item.ItemRepository;
import com.amozh.item.model.Item;
import com.amozh.operation.dto.StockOperationItemDTO;
import com.amozh.operation.dto.operations.HoldOperationCreateDTO;
import com.amozh.operation.dto.operations.InOperationCreateDTO;
import com.amozh.operation.dto.operations.StockOperationCreateDTO;
import com.amozh.operation.item.StockOperationItem;
import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.impl.HoldOperation;
import com.amozh.operation.model.impl.InOperation;
import com.amozh.storage.Storage;
import com.amozh.storage.StorageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by Andrii Mozharovskyi on 22.04.2016.
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockOperationMapper {

    private final @NonNull
    StorageRepository storageRepository;

    private final @NonNull
    ItemRepository itemRepository;

    public StockOperation toEntity(StockOperationCreateDTO dto) {
        if(dto instanceof InOperationCreateDTO) {
            return new InOperationMapper((InOperationCreateDTO) dto, new InOperation()).map();
        } else if(dto instanceof HoldOperationCreateDTO) {
            return new HoldOperationMapper((HoldOperationCreateDTO) dto, new HoldOperation()).map();
        } else {
            throw new InvalidOperationTypeException(
                    "Cannot convert to entity. Unhandled operation type: " + dto.getClass().getSimpleName());
        }
    }

    public Collection<StockOperationItem> toEntities(Collection<StockOperationItemDTO> dtos) {
        return dtos.stream().map(dto -> toEntity(dto)).collect(Collectors.toList());
    }

    public StockOperationItem toEntity(StockOperationItemDTO dto) {
        Item item = itemRepository.findOne(dto.getItemId());
        return new StockOperationItem(item, dto.getAmount(), dto.getPrice(), dto.getAmountAfter());
    }

    private abstract class BasicOperationMapper<D extends StockOperationCreateDTO, E extends StockOperation> {
        protected E entity;
        protected D dto;

        public BasicOperationMapper(D dto, E entity) { this.dto = dto; this.entity = entity; mapBasicFields(); }

        private void mapBasicFields() {
            entity.setDateTimePerformed(dto.getDateTimePerformed());
            entity.setDescription(dto.getDescription());
            Storage storage = storageRepository.findOne(dto.getStorageId());
            if(storage == null) {
                throw new IllegalArgumentException("Failed to map StockOperation - " +
                        " no storage found with id: " + dto.getStorageId());
            }
            entity.setStorage(storage);
            entity.setItems(toEntities(dto.getItems()));
        }

        public abstract E map();
    }

    private class InOperationMapper extends BasicOperationMapper<InOperationCreateDTO, InOperation> {
        public InOperationMapper(InOperationCreateDTO dto, InOperation entity) { super(dto, entity); }

        @Override
        public InOperation map() {
            entity.setSupplier(dto.getSupplier());
            return entity;
        }
    }

    private class HoldOperationMapper extends BasicOperationMapper<HoldOperationCreateDTO, HoldOperation> {
        public HoldOperationMapper(HoldOperationCreateDTO dto, HoldOperation entity) { super(dto, entity); }

        @Override
        public HoldOperation map() {
            return entity;
        }
    }

}
