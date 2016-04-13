package com.amozh.operation;

import com.amozh.Api;
import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.StockOperationItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii Mozharovskyi on 07.04.2016.
 */
@RepositoryRestResource(path = Api.RES_STOCK_OPERATION_ITEMS)
public interface StockOperationItemRepository extends PagingAndSortingRepository<StockOperationItem, String> {

    @Query("SELECT soi FROM StockOperationItem soi JOIN soi.operation o JOIN soi.item i " +
            "WHERE o.dateTimeCreated > :date")
    Collection<StockOperationItem> findAllWithOperationAndItemFromDate(@Param("date") Date date);
}
