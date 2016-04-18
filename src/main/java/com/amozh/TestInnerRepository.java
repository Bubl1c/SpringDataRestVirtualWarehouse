package com.amozh;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by Andrii Mozharovskyi on 18.04.2016.
 */
@RepositoryRestResource(path = "test_inner")
public interface TestInnerRepository extends PagingAndSortingRepository<TestInner, Long> {
    @RestResource(exported = false)
    @Override
    <S extends TestInner> S save(S entity);

    @RestResource(exported = false)
    @Override
    <S extends TestInner> Iterable<S> save(Iterable<S> entities);


}
