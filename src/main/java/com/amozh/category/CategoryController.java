package com.amozh.category;

import com.amozh.Api;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by Andrii Mozharovskyi on 11.04.2016.
 */
@RepositoryRestController
@RequestMapping(Api.CONTEXT + "/categories")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryController {

    private final @NonNull
    CategoryService categoryService;

    @RequestMapping(value = "/ingredients_tree", method = RequestMethod.GET)
    @ResponseBody
    public Resource<Map<Long, CategoryService.CategoryTreeDTO>> getTree(
            @RequestParam(value = "storage_id", required = true) Long storageId) {
        return new Resource<>(categoryService.getCategoriesWithItemsTree(storageId));
    }
}
