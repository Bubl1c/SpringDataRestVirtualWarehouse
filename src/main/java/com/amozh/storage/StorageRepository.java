package com.amozh.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Andrii Mozharovskyi on 07.04.2016.
 */
@RepositoryRestResource(path = "storages")
public interface StorageRepository extends CrudRepository<Storage, Long> {
}
