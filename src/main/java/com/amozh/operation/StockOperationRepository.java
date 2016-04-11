package com.amozh.operation;

import com.amozh.operation.StockOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Andrii Mozharovskyi on 07.04.2016.
 */
@RepositoryRestResource(path = "stock")
public interface StockOperationRepository extends CrudRepository<StockOperation, String> {
}
