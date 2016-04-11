package com.amozh.item;

import com.amozh.item.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Andrii Mozharovskyi on 07.04.2016.
 */
@RepositoryRestResource(path = "units")
public interface UnitRepository extends CrudRepository<Unit, Long> {
}
