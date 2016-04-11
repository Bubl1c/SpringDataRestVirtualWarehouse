package com.amozh;

import com.amozh.category.Category;
import com.amozh.category.CategoryContentType;
import com.amozh.category.CategoryRepository;
import com.amozh.item.*;
import com.amozh.operation.StockOperation;
import com.amozh.operation.StockOperationItem;
import com.amozh.operation.StockOperationRepository;
import com.amozh.operation.StockOperationType;
import com.amozh.storage.*;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by Andrii Mozharovskyi on 05.04.2016.
 */
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StartupService {

    private final @NonNull
    UnitRepository unitRepository;

    private final @NonNull
    StorageRepository storageRepository;

    private final @NonNull
    StorageItemRepository storageItemRepository;

    private final @NonNull
    StockOperationRepository stockOperationRepository;

    private final @NonNull
    ItemRepository itemRepository;

    private final @NonNull
    CategoryRepository categoryRepository;

    public void onStartup() {
        Unit unitPt = new Unit();
        unitPt.setName("Parts");
        unitPt.setDescription("parts");
        unitPt.setShortName("pt");
        unitRepository.save(unitPt);

        Unit unitKg = new Unit();
        unitKg.setName("Kilograms");
        unitKg.setDescription("kilos's");
        unitKg.setShortName("kg");
        unitRepository.save(unitKg);

        //=========================================
        Category catRootProd = new Category();
        catRootProd.setName("Products");
        catRootProd.setContentType(CategoryContentType.P);
        categoryRepository.save(catRootProd);

        Category catProd = new Category();
        catProd.setName("Juice");
        catProd.setContentType(CategoryContentType.P);
        catProd.setParent(catRootProd);
        categoryRepository.save(catProd);

        Category catIngr = new Category();
        catIngr.setName("Ingredients");
        catIngr.setContentType(CategoryContentType.I);
        categoryRepository.save(catIngr);

        Category catGrocery = new Category();
        catGrocery.setName("Grocery");
        catGrocery.setContentType(CategoryContentType.I);
        catGrocery.setParent(catIngr);
        categoryRepository.save(catGrocery);

        Category catFruits = new Category();
        catFruits.setName("Fruits");
        catFruits.setContentType(CategoryContentType.I);
        catFruits.setParent(catIngr);
        categoryRepository.save(catFruits);

        Category catCitrus = new Category();
        catCitrus.setName("Citrus");
        catCitrus.setContentType(CategoryContentType.I);
        catCitrus.setParent(catFruits);
        categoryRepository.save(catCitrus);

        //=========================================

        Storage storage1 = new Storage();
        storage1.setName("Storage 1");
        storage1.setType(StorageType.GENERAL);
        storageRepository.save(storage1);

        //=========================================

        Ingredient ingrApple = new Ingredient();
        ingrApple.setName("Apple");
        ingrApple.setSku("Ap");
        ingrApple.setUnits(unitKg);
        ingrApple.setCategory(catFruits);
        itemRepository.save(ingrApple);

        Ingredient ingrOrange = new Ingredient();
        ingrOrange.setName("Orange");
        ingrOrange.setSku("Or");
        ingrOrange.setUnits(unitKg);
        ingrOrange.setCategory(catCitrus);
        itemRepository.save(ingrOrange);

        Ingredient ingrGrape = new Ingredient();
        ingrGrape.setName("Grape");
        ingrGrape.setSku("Gr");
        ingrGrape.setUnits(unitKg);
        ingrGrape.setCategory(catCitrus);
        itemRepository.save(ingrGrape);

        Ingredient ingrSugar = new Ingredient();
        ingrSugar.setName("Sugar");
        ingrSugar.setSku("S");
        ingrSugar.setUnits(unitKg);
        ingrSugar.setCategory(catGrocery);
        itemRepository.save(ingrSugar);

        Ingredient ingrSalt = new Ingredient();
        ingrSalt.setName("Salt");
        ingrSalt.setSku("Salt");
        ingrSalt.setUnits(unitKg);
        ingrSalt.setCategory(catGrocery);
        itemRepository.save(ingrSalt);

        //=========================================

        Product prodAppleJuice = new Product();
        prodAppleJuice.setName("Apple juice");
        prodAppleJuice.setCategory(catProd);
        prodAppleJuice.setPrice(BigDecimal.valueOf(5));
        prodAppleJuice.setIngredients(Arrays.asList(ingrGrape, ingrSalt));
        itemRepository.save(prodAppleJuice);

        //=========================================

        StockOperation stockOperation = new StockOperation();

        StockOperationItem itemSugar = new StockOperationItem();
        itemSugar.setItem(ingrSugar);
        itemSugar.setAmount(new BigDecimal(35.555));
        itemSugar.setPrice(new BigDecimal(50));
        itemSugar.setOperation(stockOperation);

        StockOperationItem itemApple = new StockOperationItem();
        itemApple.setItem(ingrApple);
        itemApple.setAmount(new BigDecimal(5));
        itemApple.setPrice(new BigDecimal(17));
        itemApple.setOperation(stockOperation);

        stockOperation.setDateTimePerformed(new Date());
        stockOperation.setType(StockOperationType.IN);
        stockOperation.addItem(itemSugar);
        stockOperation.addItem(itemApple);
        stockOperationRepository.save(stockOperation);

        //=========================================

        StorageItem storageItemSugar = new StorageItem();
        storageItemSugar.setItem(itemSugar.getItem());
        storageItemSugar.setStorage(storage1);
        storageItemSugar.setAmount(itemSugar.getAmount());
        storageItemSugar.setHold(new BigDecimal(72.456));
        storageItemRepository.save(storageItemSugar);

        StorageItem storageItemApple = new StorageItem();
        storageItemApple.setItem(itemApple.getItem());
        storageItemApple.setStorage(storage1);
        storageItemApple.setAmount(itemApple.getAmount());
        storageItemRepository.save(storageItemApple);

        //========================================= NO OPEARTIONS

        StorageItem storageItemSalt = new StorageItem();
        storageItemSalt.setItem(ingrSalt);
        storageItemSalt.setStorage(storage1);
        storageItemSalt.setAmount(BigDecimal.valueOf(249.21));
        storageItemSalt.setHold(BigDecimal.valueOf(566.34));
        storageItemRepository.save(storageItemSalt);

        StorageItem storageItemOrange = new StorageItem();
        storageItemOrange.setItem(ingrOrange);
        storageItemOrange.setStorage(storage1);
        storageItemOrange.setAmount(new BigDecimal(14));
        storageItemOrange.setHold(new BigDecimal(3));
        storageItemRepository.save(storageItemOrange);

        StorageItem storageItemGrape = new StorageItem();
        storageItemGrape.setItem(ingrGrape);
        storageItemGrape.setStorage(storage1);
        storageItemGrape.setAmount(new BigDecimal(149));
        storageItemGrape.setHold(new BigDecimal(33));
        storageItemRepository.save(storageItemGrape);

    }
}
