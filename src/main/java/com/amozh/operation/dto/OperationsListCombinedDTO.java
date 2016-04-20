package com.amozh.operation.dto;

import com.amozh.operation.item.StockOperationItem;
import com.amozh.operation.model.StockOperationType;
import com.amozh.utils.DTORestResource;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Andrii Mozharovskyi on 19.04.2016.
 */
@Data
@NoArgsConstructor
@DTORestResource(singleRel = "operation", collectionRel = "operations")
public class OperationsListCombinedDTO {

    private String id;

    private String itemName;

    private BigDecimal amount;

    private BigDecimal price;

    private BigDecimal amountAfter;

    private String operationId;

    private StockOperationType operationType;

    private Date operationDateTimeCreated;

    private Date operationDateTimePerformed;

    public OperationsListCombinedDTO(StockOperationItem item) {
        this.id = item.getId();
        this.itemName = item.getItem().getName();
        this.amount = item.getAmount();
        this.price = item.getPrice();
        this.amountAfter = item.getAmountAfter();

        this.operationId = item.getOperation().getId();
        this.operationType = item.getOperation().getType();
        this.operationDateTimeCreated = item.getOperation().getDateTimeCreated();
        this.operationDateTimePerformed = item.getOperation().getDateTimePerformed();
    }
}
