package com.amozh.utils;

import java.lang.annotation.*;

/**
 * Created by Andrii Mozharovskyi on 20.04.2016.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DTORestResource {
    String singleRel();
    String collectionRel();
}
