package com.amozh.config;

import com.amozh.item.ItemRepository;
import com.amozh.item.model.Ingredient;
import com.amozh.item.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;
import org.springframework.stereotype.Component;

/**
 * Created by Andrii Mozharovskyi on 18.04.2016.
 */
//@Component
//public class CustomRepositoryRestConfiguration extends RepositoryRestConfigurerAdapter {
//
//    @Autowired
//    ItemRepository repository;
//
//    @Override
//    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//
////        config.withCustomEntityLookup()
////                .forRepository(ItemRepository.class)
////                .withIdMapping(entity -> entity.getId())
////                .withLookup( -> repository.findOne(Long.parseLong(id)));
//    }
//}
