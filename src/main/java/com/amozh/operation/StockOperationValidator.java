package com.amozh.operation;

import com.amozh.operation.dto.StockOperationItemDTO;
import com.amozh.operation.dto.operations.StockOperationCreateDTO;

import javax.xml.bind.ValidationException;

/**
 * Created by Andrii Mozharovskyi on 22.04.2016.
 */
public class StockOperationValidator {
    public static void validate(StockOperationCreateDTO dto) throws ValidationException {
        dto.getItems().stream().forEach(itemDto -> validate(itemDto));
        //TODO: validate dto
    }

    public static void validate(StockOperationItemDTO dto) {
        //TODO: validate dto
    }
}
