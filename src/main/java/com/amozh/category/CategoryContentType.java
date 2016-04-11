package com.amozh.category;

/**
 * Created by Andrii Mozharovskyi on 06.04.2016.
 */

/**
 * Important!
 * Enum constants saved to DB as String (constant name).
 * Make sure that the name length is less than limitations to DB table columns.
 */
public enum CategoryContentType {
    /**
     * Products
     */
    P,
    /**
     * Ingredients
     */
    I,
    /**
     * Products and Ingredients
     */
    P_I
}
