package com.amozh.utils;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;

/**
 * Created by Andrii Mozharovskyi on 20.04.2016.
 */
public class ResourceUtils {

    public static <T> PagedResources<T> toPagedResources(Page<T> page, Iterable<Link> links) {
        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                page.getSize(), page.getNumber(), page.getTotalElements(), page.getTotalPages());
        return new PagedResources<>(page.getContent(), metadata, links);
    }

}
