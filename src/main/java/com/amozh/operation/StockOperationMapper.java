package com.amozh.operation;

import com.amozh.exception.InvalidOperationTypeException;
import com.amozh.item.ItemRepository;
import com.amozh.item.model.Item;
import com.amozh.operation.dto.HoldOperationDTO;
import com.amozh.operation.dto.InOperationDTO;
import com.amozh.operation.dto.StockOperationDTO;
import com.amozh.operation.dto.StockOperationItemDTO;
import com.amozh.operation.item.StockOperationItem;
import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.impl.HoldOperation;
import com.amozh.operation.model.impl.InOperation;
import com.amozh.storage.Storage;
import com.amozh.storage.StorageRepository;
import com.sun.javaws.exceptions.InvalidArgumentException;
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

    public StockOperation toEntity(StockOperationDTO dto) {
        if(dto instanceof InOperationDTO) {
            return new InOperationMapper((InOperationDTO) dto, new InOperation()).map();
        } else if(dto instanceof HoldOperationDTO) {
            return new HoldOperationMapper((HoldOperationDTO) dto, new HoldOperation()).map();
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

    private abstract class BasicOperationMapper<D extends StockOperationDTO, E extends StockOperation> {
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

    private class InOperationMapper extends BasicOperationMapper<InOperationDTO, InOperation> {
        public InOperationMapper(InOperationDTO dto, InOperation entity) { super(dto, entity); }

        @Override
        public InOperation map() {
            entity.setSupplier(dto.getSupplier());
            return entity;
        }
    }

    private class HoldOperationMapper extends BasicOperationMapper<HoldOperationDTO, HoldOperation> {
        public HoldOperationMapper(HoldOperationDTO dto, HoldOperation entity) { super(dto, entity); }

        @Override
        public HoldOperation map() {
            return entity;
        }
    }

}
