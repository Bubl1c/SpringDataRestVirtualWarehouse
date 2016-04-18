package com.amozh.operation;

import com.amozh.Api;
import com.amozh.Const;
import com.amozh.operation.model.StockOperation;
import com.amozh.operation.model.StockOperationItem;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Andrii Mozharovskyi on 18.04.2016.
 */
@RepositoryRestController
@RequestMapping(Api.RES_COLLECTION_STOCK_OPERATIONS)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockOperationController {
    private final @NonNull
    StockOperationRepository stockOperationRepository;

    private final @NonNull
    StockOperationItemRepository stockOperationItemRepository;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public StockOperation save(@RequestBody StockOperation stockOperation) {
        //Map links to operations from items
        stockOperation.getItems().stream().forEach(i -> i.setOperation(stockOperation));
        return stockOperationRepository.save(stockOperation);
    }

//    @RequestMapping(value = "/items", method = RequestMethod.POST)
//    @ResponseBody
//    @JsonView(StockOperationItem.OperationsList.class)
//    public List<StockOperationItem> listItems(
//            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = Const.DATE_TIME_PATTERN) Date date,
//            @RequestParam(value = "itemId", required = false) Long itemId,
//            Pageable pageable) {
//        return stockOperationItemRepository.findAllWithOperationAndItemStartingFromDate(date, itemId, pageable);
//    }
}
