package com.amozh.operation;

import com.amozh.operation.dto.OperationsListCombinedDTO;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.IdentifiableResourceAssemblerSupport;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Andrii Mozharovskyi on 20.04.2016.
 */
public class NoLinksResourceAssembler<T extends Identifiable<Serializable>> extends IdentifiableResourceAssemblerSupport<T, Resource> {

    public NoLinksResourceAssembler(Class<?> controllerClass) {
        super(controllerClass, Resource.class);
    }

    @Override
    public Resource<T> toResource(T object) {
        return new Resource<>(object, new ArrayList<>());
    }
}
