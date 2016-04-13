package com.amozh.operation.model.impl;

import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.StockOperationType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Andrii Mozharovskyi on 12.04.2016.
 */
@Entity
@Table(name = "in_operation")
@Data
public class InOperation extends StockOperation {

    @Column(length = 50)
    private String supplier;

    @Override
    public StockOperationType getType() {
        return StockOperationType.IN;
    }
}
