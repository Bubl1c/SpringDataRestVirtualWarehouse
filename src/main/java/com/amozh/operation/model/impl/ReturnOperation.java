package com.amozh.operation.model.impl;

import com.amozh.operation.model.StockOperation;
import lombok.Data;

/**
 * Created by Andrii Mozharovskyi on 12.04.2016.
 */
//@Entity
//@Table(name = "return_operation")
@Data
public class ReturnOperation extends StockOperation {

    private Long order;

}
