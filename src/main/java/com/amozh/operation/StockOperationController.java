package com.amozh.operation;

import com.amozh.Api;
import com.amozh.Const;
import com.amozh.TestDTo;
import com.amozh.operation.dto.InOperationDTO;
import com.amozh.operation.dto.OperationsListCombinedDTO;
import com.amozh.operation.dto.StockOperationDTO;
import com.amozh.operation.item.StockOperationItem;
import com.amozh.operation.model.StockOperation;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Andrii Mozharovskyi on 18.04.2016.
 */
//@RestController
@Validated
@RepositoryRestController
@RequestMapping(Api.RES_COLLECTION_STOCK_OPERATIONS)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockOperationController {

    private final @NonNull
    StockOperationService stockOperationService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> save(@Valid @RequestBody InOperationDTO stockOperationDTO) {
        StockOperation stockOperation = stockOperationService.save(stockOperationDTO);
        return ResponseEntity.ok(stockOperation.getId());
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity update(@Valid @RequestBody TestDTo dto) {
        return ResponseEntity.ok(dto);
    }

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
                = stockOperationService.searchItems(storageId, itemId, fromDate, toDate, operationTypes, pageable);
        Page<OperationsListCombinedDTO> itemDtosPage
                = itemsPage.map((stockOperationItem) -> new OperationsListCombinedDTO(stockOperationItem));
        return ResponseEntity.ok(ResourceUtils.toPagedResources(itemDtosPage, new ArrayList<>()));
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleMethodArgumentNotValidException(
            MethodArgumentNotValidException error) {
        return "Bad request: " + error.getMessage();
    }
}
