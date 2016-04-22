package com.amozh.operation.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Andrii Mozharovskyi on 22.04.2016.
 */
@Data
public class StockOperationItemDTO {

    private String operationId;

    @NotNull
    @Min(1)
    private Long itemId;

    @NotNull
    @Min(0)
    private BigDecimal amount = new BigDecimal(0);

    @NotNull
    @Min(0)
    private BigDecimal price = new BigDecimal(0);

    @NotNull
    private BigDecimal amountAfter = new BigDecimal(0);
}
