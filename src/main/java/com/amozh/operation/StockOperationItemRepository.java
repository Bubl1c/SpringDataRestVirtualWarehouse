package com.amozh.operation;

import com.amozh.Api;
import com.amozh.Const;
import com.amozh.operation.model.StockOperationItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii Mozharovskyi on 07.04.2016.
 */
@RepositoryRestResource(path = Api.RES_COLLECTION_STOCK_OPERATION_ITEMS)
public interface StockOperationItemRepository extends PagingAndSortingRepository<StockOperationItem, String> {

    @RestResource(rel = "list", path = "list")
    @Query("SELECT soi FROM StockOperationItem soi JOIN FETCH soi.operation o JOIN FETCH soi.item i "
            + " WHERE (:date IS NULL OR o.dateTimePerformed > :date) AND (:itemId IS NULL OR i.id = :itemId)")
    List<StockOperationItem> findAllWithOperationAndItemStartingFromDate(
            @Nullable @Param("date") @DateTimeFormat(pattern = Const.DATE_TIME_PATTERN) Date date,
            @Nullable @Param("itemId") Long itemId,
            Pageable pageable);

}
