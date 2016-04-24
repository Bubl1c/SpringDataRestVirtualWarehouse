package com.amozh.operation.dto.operations;

import com.amozh.Const;
import com.amozh.operation.dto.StockOperationItemDTO;
import com.amozh.operation.model.StockOperationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Andrii Mozharovskyi on 22.04.2016.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InOperationCreateDTO.class, name = StockOperationType.IN_OPERATION_NAME),
        @JsonSubTypes.Type(value = HoldOperationCreateDTO.class, name = StockOperationType.HOLD_OPERATION_NAME) })
@Data
@NoArgsConstructor
public abstract class StockOperationCreateDTO {

    @NotNull
    @Size(max = 1000)
    private String description;

    @NotNull
    @Valid
    private Collection<StockOperationItemDTO> items;

    @NotNull
    @Min(1)
    private Long storageId;

    @JsonFormat(pattern = Const.DATE_TIME_PATTERN)
    @NotNull
    private Date dateTimePerformed;

    @Min(1)
    private int someShit;

}
