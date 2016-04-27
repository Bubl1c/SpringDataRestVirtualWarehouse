package com.amozh.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by Andrii Mozharovskyi on 27.04.2016.
 */
@Projection(name = "detailed", types = {StorageItem.class})
public interface StorageItemWithItemAndStorageProjection {

    @Value("#{target.storage.id}")
    Long getStorageId();

    @Value("#{target.item.id}")
    Long getItemId();

    @Value("#{target.item.name}")
    String getItemName();

    String getAmount();

    String getHold();

}
