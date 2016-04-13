package com.amozh.operation.model.impl;

import com.amozh.operation.model.StockOperation;
import com.amozh.storage.Storage;
import lombok.Data;

import javax.persistence.*;

/**
 * Created by Andrii Mozharovskyi on 12.04.2016.
 */
//@Entity
//@Table(name = "move_operation")
@Data
public class MoveOperation extends StockOperation {

    @ManyToOne
    @JoinColumn(name = "from_storage_id")
    private Storage from;

    @ManyToOne
    @JoinColumn(name = "to_storage_id")
    private Storage to;

}
