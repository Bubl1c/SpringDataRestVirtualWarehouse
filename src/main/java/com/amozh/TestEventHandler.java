package com.amozh;

import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii Mozharovskyi on 18.04.2016.
 */
@Component
@RepositoryEventHandler(TestOuter.class)
public class TestEventHandler {
    @HandleBeforeCreate
    public void handlebc(TestOuter entity){
        entity.getItems().stream().forEach(i -> i.setParent(entity));
        int i = 0;
    }
}
