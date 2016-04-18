package com.amozh.operation.model;

/**
 * Created by Andrii Mozharovskyi on 06.04.2016.
 */
public enum StockOperationType {
    IN, MOVE, SALE, RETURN, DECOMMISSION, HOLD;

    public static final String IN_OPERATION_NAME = "in";
    public static final String MOVE_OPERATION_NAME = "move";
    public static final String SALE_OPERATION_NAME = "sale";
    public static final String RETURN_OPERATION_NAME = "return";
    public static final String DECOMMISSION_OPERATION_NAME = "decommission";
    public static final String HOLD_OPERATION_NAME = "hold";

}
