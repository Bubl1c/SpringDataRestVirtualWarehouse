package com.amozh.category;

import com.amozh.item.Item;
import com.amozh.storage.Storage;
import com.amozh.storage.StorageItem;
import com.amozh.storage.StorageItemRepository;
import com.amozh.storage.StorageRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Data
    @NoArgsConstructor
    public class CategoryTreeDTO {
        private long id;
        private String name;
        private CategoryContentType contentType;
        private long parentId;
        private Collection<CategoryTreeItemDTO> items = new ArrayList<>();

        public CategoryTreeDTO(long id, String name, CategoryContentType contentType, long parentId) {
            this.id = id;
            this.name = name;
            this.contentType = contentType;
            this.parentId = parentId;
        }

        public void addItem(CategoryTreeItemDTO item) {
            items.add(item);
        }
    }

    @Data
    @NoArgsConstructor
    public class CategoryTreeItemDTO {
        private long id;
        private String name;
        private String units;
        private BigDecimal stock;
        private BigDecimal hold;

        public CategoryTreeItemDTO(long id, String name, BigDecimal stock, BigDecimal hold) {
            this.id = id;
            this.name = name;
            this.stock = stock;
            this.hold = hold;
        }
    }

    public Map<Long, CategoryTreeDTO> getCategoriesWithItemsTree(Long storageId) {
        Storage storage = storageRepository.findOne(storageId);
        Collection<Category> allCategories = categoryRepository.findForIngredientsTree();
        List<StorageItem> storageItems = storageItemRepository.getByStorageWithItemAndItemCategory(storage);

        Map<Long, CategoryTreeDTO> categories = convert(allCategories);
        mapStorageItemsToCategoryTree(storageItems, categories);

        return categories;
    }

    public void mapStorageItemsToCategoryTree(final Collection<StorageItem> storageItems,
                                                                    Map<Long, CategoryTreeDTO> categories) {
        storageItems.stream().forEach(si -> {
            Item item = si.getItem();
            Category itemCategory = item.getCategory();
            CategoryTreeDTO categoryTreeDTO = categories.get(itemCategory.getId());
            if(categoryTreeDTO != null) {
                categoryTreeDTO.addItem(
                        new CategoryTreeItemDTO(item.getId(), item.getName(), si.getAmount(), si.getHold()));
            }
        });
    }

    public Map<Long, CategoryTreeDTO> convert(final Collection<Category> categories) {
        return categories.stream().collect(
                Collectors.toMap(
                        Category::getId,
                        c -> new CategoryTreeDTO(
                                c.getId(),
                                c.getName(),
                                c.getContentType(),
                                c.getParent() != null ? c.getParent().getId() : -1)
                ));
    }

}
