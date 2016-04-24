package com.amozh.item.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Andrii Mozharovskyi on 05.04.2016.
 */
@Entity
@Table(name="mb_Unit")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class Unit {
    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Size(max = 10)
    @Column(nullable = false, length = 10)
    private String name;

    @NotNull
    @Size(min = 1, max = 5)
    @Column(nullable = false, length = 5)
    private String shortName;

    @Size(max = 255)
    private String description;
}
