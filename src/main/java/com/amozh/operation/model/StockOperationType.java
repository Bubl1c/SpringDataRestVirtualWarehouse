package com.amozh.operation.model;

import com.amozh.operation.model.impl.*;

/**
 * Created by Andrii Mozharovskyi on 06.04.2016.
 */
public enum StockOperationType {
    IN {
        @Override public Class<InOperation> getClazz() { return InOperation.class; }
    }, MOVE {
        @Override public Class<MoveOperation> getClazz() { return MoveOperation.class; }
    }, SALE {
        @Override public Class<SaleOperation> getClazz() { return SaleOperation.class; }
    }, RETURN {
        @Override public Class<ReturnOperation> getClazz() { return ReturnOperation.class; }
    }, DECOMMISSION {
        @Override public Class<DecommissionOperation> getClazz() { return DecommissionOperation.class; }
    }, HOLD {
        @Override public Class<HoldOperation> getClazz() { return HoldOperation.class; }
    };

    public static final String IN_OPERATION_NAME = "in";
    public static final String MOVE_OPERATION_NAME = "move";
    public static final String SALE_OPERATION_NAME = "sale";
    public static final String RETURN_OPERATION_NAME = "return";
    public static final String DECOMMISSION_OPERATION_NAME = "decommission";
    public static final String HOLD_OPERATION_NAME = "hold";

    //For optimization - place to @DiscriminatorValue
    public static final int IN_OPERATION_ID = 0;
    public static final int MOVE_OPERATION_ID = 1;
    public static final int SALE_OPERATION_ID = 2;
    public static final int RETURN_OPERATION_ID = 3;
    public static final int DECOMMISSION_OPERATION_ID = 4;
    public static final int HOLD_OPERATION_ID = 5;

    public abstract <T extends StockOperation> Class<T> getClazz();

}
