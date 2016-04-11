package com.amozh.item;

import com.amozh.item.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by Andrii Mozharovskyi on 08.04.2016.
 */
@RepositoryRestResource
public interface ProductRepository extends CrudRepository<Product, Long> {
}
