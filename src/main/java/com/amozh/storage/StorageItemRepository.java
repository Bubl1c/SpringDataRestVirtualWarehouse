package com.amozh.storage;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


/**
 * Created by Andrii Mozharovskyi on 07.04.2016.
 */
@RepositoryRestResource(path = "storage_items")
public interface StorageItemRepository extends CrudRepository<StorageItem, Long> {

    @Query("select si from StorageItem si JOIN FETCH si.item i JOIN FETCH i.category c " +
            "where si.storage = :storage and TYPE(i) = Ingredient")
    List<StorageItem>  getByStorageWithItemAndItemCategory(@Param("storage") Storage storage);
}
