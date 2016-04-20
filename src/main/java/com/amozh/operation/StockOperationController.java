package com.amozh.operation;

import com.amozh.Api;
import com.amozh.Const;
import com.amozh.operation.dto.OperationsListCombinedDTO;
import com.amozh.operation.item.StockOperationItem;
import com.amozh.operation.model.StockOperationType;
import com.amozh.utils.ResourceUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Andrii Mozharovskyi on 18.04.2016.
 */
@RepositoryRestController
@RequestMapping(Api.RES_COLLECTION_STOCK_OPERATIONS)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockOperationController {

    private final @NonNull
    StockOperationService stockOperationService;

    @RestResource(rel = "operations")
    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ResponseEntity<PagedResources<OperationsListCombinedDTO>> listItems(
            @RequestParam(name = "storageId", required = true) Long storageId,
            @RequestParam(name = "itemId", required = false) Long itemId,
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = Const.DATE_TIME_PATTERN) Date fromDate,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = Const.DATE_TIME_PATTERN) Date toDate,
            @RequestParam(name = "operations", required = false) StockOperationType[] operationTypes,
            Pageable pageable) {
        Page<StockOperationItem> itemsPage
                = stockOperationService.listItems(storageId, itemId, fromDate, toDate, operationTypes, pageable);
        Page<OperationsListCombinedDTO> itemDtosPage
                = itemsPage.map((stockOperationItem) -> new OperationsListCombinedDTO(stockOperationItem));
        return ResponseEntity.ok(ResourceUtils.toPagedResources(itemDtosPage, new ArrayList<>()));
    }
}
