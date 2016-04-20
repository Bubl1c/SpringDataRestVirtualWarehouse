package com.amozh.utils;

import org.springframework.hateoas.core.EvoInflectorRelProvider;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii Mozharovskyi on 20.04.2016.
 */
@Component
public class DTOSupportedRelProvider extends EvoInflectorRelProvider {
    @Override
    public String getItemResourceRelFor(Class<?> type) {
        DTORestResource dtoRestResource = type.getAnnotation(DTORestResource.class);
        return dtoRestResource == null ? super.getItemResourceRelFor(type) : dtoRestResource.singleRel();
    }

    @Override
    public String getCollectionResourceRelFor(Class<?> type) {
        DTORestResource dtoRestResource = type.getAnnotation(DTORestResource.class);
        return dtoRestResource == null ? super.getCollectionResourceRelFor(type) : dtoRestResource.collectionRel();
    }
}
