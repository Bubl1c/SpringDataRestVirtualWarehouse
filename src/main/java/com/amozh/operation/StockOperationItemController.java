package com.amozh.operation;

import com.amozh.Api;
import com.amozh.item.ItemRepository;
import com.amozh.item.model.Item;
import com.amozh.operation.model.StockOperationItem;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

/**
 * Created by Andrii Mozharovskyi on 13.04.2016.
 */
@RestController
@RequestMapping(Api.CONTEXT + "/" + Api.RES_STOCK_OPERATION_ITEMS)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StockOperationItemController {

    private final @NonNull StockOperationItemRepository stockOperationItemRepository;
    private final @NonNull
    ItemRepository itemRepository;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @JsonView(StockOperationItem.OperationsList.class)
    public Iterable<StockOperationItem> findAllOperationItemsAsSingleListForLast30Days() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);

        Iterable<StockOperationItem> operations
                = stockOperationItemRepository.findAllWithOperationAndItemFromDate(calendar.getTime());

        return operations;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    @JsonView(Item.TestJsonView.class)
    public Iterable<Item> findAllItems() {
        return itemRepository.findAll();
    }

}
