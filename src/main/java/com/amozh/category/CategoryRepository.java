package com.amozh.category;

import com.amozh.Api;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;

/**
 * Created by Andrii Mozharovskyi on 07.04.2016.
 */
@RepositoryRestResource(path = Api.RES_COLLECTION_CATEGORIES)
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.parent WHERE c.contentType = com.amozh.category.CategoryContentType.I")
    Collection<Category> findForIngredientsTree();

    @RestResource(rel = "by_type", path = "by_type")
    Collection<Category> findByContentType(@Param("type") CategoryContentType contentType);

}
