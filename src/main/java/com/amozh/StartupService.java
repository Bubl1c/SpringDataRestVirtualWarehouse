package com.amozh;

import com.amozh.category.Category;
import com.amozh.category.CategoryContentType;
import com.amozh.category.CategoryRepository;
import com.amozh.item.*;
import com.amozh.item.model.Ingredient;
import com.amozh.item.model.Product;
import com.amozh.item.model.Unit;
import com.amozh.operation.model.StockOperation;
import com.amozh.operation.item.StockOperationItem;
import com.amozh.operation.StockOperationRepository;
import com.amozh.operation.model.impl.HoldOperation;
import com.amozh.operation.model.impl.InOperation;
import com.amozh.storage.*;
import com.amozh.supplier.Supplier;
import com.amozh.supplier.SupplierRepository;
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

    private final @NonNull
    SupplierRepository supplierRepository;

    public void onStartup() {

        Supplier supplier = new Supplier("Andrii Mozharovskyi");
        supplier.setDescription("Best supplier ever!");
        supplierRepository.save(supplier);

        Supplier supplier2 = new Supplier("Anti monopoly");
        supplier2.setDescription("Not so good!");
        supplierRepository.save(new Supplier());

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

        Storage storage2 = new Storage();
        storage2.setName("Storage 2");
        storage2.setType(StorageType.INSIDE_BUSINESS);
        storageRepository.save(storage2);

        //=========================================

        Ingredient ingrApple = new Ingredient();
        ingrApple.setName("Apple");
        ingrApple.setSku("Ap");
        ingrApple.setUnits(unitKg.getName());
        ingrApple.setCategory(catFruits);
        itemRepository.save(ingrApple);

        Ingredient ingrOrange = new Ingredient();
        ingrOrange.setName("Orange");
        ingrOrange.setSku("Or");
        ingrOrange.setUnits(unitKg.getName());
        ingrOrange.setCategory(catCitrus);
        itemRepository.save(ingrOrange);

        Ingredient ingrGrape = new Ingredient();
        ingrGrape.setName("Grape");
        ingrGrape.setSku("Gr");
        ingrGrape.setUnits(unitKg.getName());
        ingrGrape.setCategory(catCitrus);
        itemRepository.save(ingrGrape);

        Ingredient ingrSugar = new Ingredient();
        ingrSugar.setName("Sugar");
        ingrSugar.setSku("S");
        ingrSugar.setUnits(unitKg.getName());
        ingrSugar.setCategory(catGrocery);
        itemRepository.save(ingrSugar);

        Ingredient ingrSalt = new Ingredient();
        ingrSalt.setName("Salt");
        ingrSalt.setSku("Salt");
        ingrSalt.setUnits(unitKg.getName());
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

        InOperation inOperation = new InOperation();

        StockOperationItem itemSugar = new StockOperationItem();
        itemSugar.setItem(ingrSugar);
        itemSugar.setAmount(BigDecimal.valueOf(35.555));
                itemSugar.setPrice(BigDecimal.valueOf(50));
        itemSugar.setOperation(inOperation);

        StockOperationItem itemApple = new StockOperationItem();
        itemApple.setItem(ingrApple);
        itemApple.setAmount(BigDecimal.valueOf(5));
        itemApple.setPrice(BigDecimal.valueOf(17));
        itemApple.setOperation(inOperation);

        inOperation.setDateTimePerformed(new Date());
        inOperation.setStorage(storage1);
        inOperation.addItem(itemSugar);
        inOperation.addItem(itemApple);
        inOperation.setSupplier(supplier.getName());
        stockOperationRepository.save(inOperation);

        StockOperation holdOperation = new HoldOperation();

        StockOperationItem itemSugarHold = new StockOperationItem();
        itemSugarHold.setItem(ingrSugar);
        itemSugarHold.setAmount(BigDecimal.valueOf(35.55));
        itemSugarHold.setPrice(BigDecimal.valueOf(50));
        itemSugarHold.setOperation(holdOperation);

        holdOperation.setDateTimePerformed(new Date(100000));
        holdOperation.setStorage(storage2);
        holdOperation.addItem(itemSugarHold);
        stockOperationRepository.save(holdOperation);

        //=========================================

        StorageItem storageItemSugar = new StorageItem();
        storageItemSugar.setItem(ingrSugar);
        storageItemSugar.setStorage(storage2);
        storageItemSugar.setAmount(BigDecimal.valueOf(33));
        storageItemSugar.setHold(BigDecimal.valueOf(72.456));
        storageItemRepository.save(storageItemSugar);

        StorageItem storageItemApple = new StorageItem();
        storageItemApple.setItem(ingrApple);
        storageItemApple.setStorage(storage1);
        storageItemApple.setAmount(BigDecimal.valueOf(77));
        storageItemRepository.save(storageItemApple);

        //========================================= NO OPERATIONS

        StorageItem storageItemSalt = new StorageItem();
        storageItemSalt.setItem(ingrSalt);
        storageItemSalt.setStorage(storage1);
        storageItemSalt.setAmount(BigDecimal.valueOf(249.21));
        storageItemSalt.setHold(BigDecimal.valueOf(566.34));
        storageItemRepository.save(storageItemSalt);

        StorageItem storageItemOrange = new StorageItem();
        storageItemOrange.setItem(ingrOrange);
        storageItemOrange.setStorage(storage1);
        storageItemOrange.setAmount(BigDecimal.valueOf(14));
        storageItemOrange.setHold(BigDecimal.valueOf(3));
        storageItemRepository.save(storageItemOrange);

        StorageItem storageItemGrape = new StorageItem();
        storageItemGrape.setItem(ingrGrape);
        storageItemGrape.setStorage(storage1);
        storageItemGrape.setAmount(BigDecimal.valueOf(149));
        storageItemGrape.setHold(BigDecimal.valueOf(33));
        storageItemRepository.save(storageItemGrape);

    }
}
