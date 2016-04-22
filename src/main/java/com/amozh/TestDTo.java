package com.amozh;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * Created by Andrii Mozharovskyi on 22.04.2016.
 */
@Data
public class TestDTo {
    @Size(min = 5, max = 50)
    private String name;

    @NotNull
    @Min(5)
    @Max(150)
    private Integer age;
}
