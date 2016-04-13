package com.amozh.item;

import com.amozh.item.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by Andrii Mozharovskyi on 07.04.2016.
 */
@RepositoryRestResource
public interface ItemRepository extends CrudRepository<Item, Long> {
    @Override
    @RestResource(exported = false)
    <S extends Item> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Item> Iterable<S> save(Iterable<S> entities);
}
