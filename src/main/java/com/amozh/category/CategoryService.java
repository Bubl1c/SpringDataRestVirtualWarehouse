package com.amozh.category;

import com.amozh.category.dto.CategoryTreeDTO;
import com.amozh.category.dto.CategoryTreeItemDTO;
import com.amozh.item.model.Item;
import com.amozh.storage.Storage;
import com.amozh.storage.StorageItem;
import com.amozh.storage.StorageItemRepository;
import com.amozh.storage.StorageRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Andrii Mozharovskyi on 11.04.2016.
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {

    private final @NonNull
    CategoryRepository categoryRepository;

    private final @NonNull
    StorageItemRepository storageItemRepository;

    private final @NonNull
    StorageRepository storageRepository;

    public Map<Long, CategoryTreeDTO> getCategoriesWithItemsTree(Long storageId) {
        Storage storage = storageRepository.findOne(storageId);
        Collection<Category> allCategories = categoryRepository.findForIngredientsTree();
        List<StorageItem> storageItems = storageItemRepository.getByStorageWithItemAndItemCategory(storage);

        Map<Long, CategoryTreeDTO> categories = convert(allCategories);
        mapStorageItemsToCategories(storageItems, categories);

        return categories;
    }

    private void mapStorageItemsToCategories(final Collection<StorageItem> storageItems,
                                             Map<Long, CategoryTreeDTO> categories) {
        storageItems.stream().forEach(si -> {
            Item item = si.getItem();
            Category itemCategory = item.getCategory();
            CategoryTreeDTO categoryTreeDTO = categories.get(itemCategory.getId());
            if(categoryTreeDTO != null) {
                categoryTreeDTO.addItem(
                        new CategoryTreeItemDTO(si.getId(), item.getName(), si.getAmount(), si.getHold()));
            }
        });
    }

    private Map<Long, CategoryTreeDTO> convert(final Collection<Category> categories) {
        return categories.stream().collect(
                Collectors.toMap(
                        Category::getId,
                        c -> new CategoryTreeDTO(
                                c.getId(),
                                c.getName(),
                                c.getContentType(),
                                c.getParent() != null ? c.getParent().getId() : null)
                ));
    }

}
