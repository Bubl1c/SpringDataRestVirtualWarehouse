package com.amozh.operation.dto.operations;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by Andrii Mozharovskyi on 22.04.2016.
 */
@Data
@NoArgsConstructor
public class InOperationCreateDTO extends StockOperationCreateDTO {
    @NotEmpty
    @Size(max=50)
    private String supplier;
}
