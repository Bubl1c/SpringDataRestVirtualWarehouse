package com.amozh.item;

import com.amozh.Api;
import com.amozh.item.model.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Andrii Mozharovskyi on 07.04.2016.
 */
@RepositoryRestResource(path = Api.RES_COLLECTION_UNITS)
public interface UnitRepository extends CrudRepository<Unit, Long> {
}
