package com.amozh.supplier;

import com.amozh.Api;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Andrii Mozharovskyi on 21.04.2016.
 */
@RepositoryRestResource(path = Api.RES_COLLECTION_SUPPLIERS,
        collectionResourceRel = Api.RES_COLLECTION_SUPPLIERS, itemResourceRel = Api.RES_SINGLE_SUPPLIER)
public interface SupplierRepository extends CrudRepository<Supplier, Long> {

    Supplier findByName(@Param("name") String name);

}
