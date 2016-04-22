package com.amozh.operation;

import com.amozh.Api;
import com.amozh.operation.model.StockOperation;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.transaction.Transactional;

/**
 * Created by Andrii Mozharovskyi on 07.04.2016.
 */
@RepositoryRestResource(path = Api.RES_COLLECTION_STOCK_OPERATIONS)
public interface StockOperationRepository extends PagingAndSortingRepository<StockOperation, String> {
}
