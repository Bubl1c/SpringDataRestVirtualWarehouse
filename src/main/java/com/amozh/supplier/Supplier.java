package com.amozh.supplier;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Andrii Mozharovskyi on 21.04.2016.
 */
@Entity
@Table(name = "mb_supplier")
@Data
@NoArgsConstructor
public class Supplier {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;

    public Supplier(String name) {
        this.name = name;
    }

}
