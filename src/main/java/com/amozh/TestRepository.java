package com.amozh;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Andrii Mozharovskyi on 18.04.2016.
 */
@RepositoryRestResource(path = "test")
public interface TestRepository extends PagingAndSortingRepository<TestOuter, Long> {
}
