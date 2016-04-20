package com.amozh.operation.item;

import com.amozh.Api;
import com.amozh.Const;
import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.StockOperationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.format.annotation.DateTimeFormat;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii Mozharovskyi on 07.04.2016.
 */
@RepositoryRestResource(path = Api.RES_COLLECTION_STOCK_OPERATION_ITEMS, exported = true)
public interface StockOperationItemRepository extends PagingAndSortingRepository<StockOperationItem, String> {

    String searchAllWithOperationAndItemQueryConditions =
            " WHERE o.storage.id = :storageId " +
            "   AND (:itemId IS NULL OR i.id = :itemId)" +
            "   AND (:fromDate IS NULL OR o.dateTimePerformed >= :fromDate) " +
            "   AND (:toDate IS NULL OR o.dateTimePerformed < :toDate) " +
            "   AND TYPE(o) IN (:operationTypes)) ";

    @Query(value = "SELECT soi FROM StockOperationItem soi JOIN FETCH soi.operation o JOIN FETCH soi.item i " +
            searchAllWithOperationAndItemQueryConditions,
    countQuery = "SELECT count(soi) FROM StockOperationItem soi JOIN soi.operation o JOIN soi.item i " +
            searchAllWithOperationAndItemQueryConditions)
    Page<StockOperationItem> searchAllWithOperationAndItem(
            @NotNull @Param("storageId") Long storageId,
            @Nullable @Param("itemId") Long itemId,
            @Nullable @Param("fromDate") Date fromDate,
            @Nullable @Param("toDate") Date toDate,
            @Nullable @Param("operationTypes") Iterable<Class<? extends StockOperation>> operationTypes,
            Pageable pageable);

}
