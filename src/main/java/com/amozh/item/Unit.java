package com.amozh.item;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private String name;

    private String shortName;

    private String description;
}
