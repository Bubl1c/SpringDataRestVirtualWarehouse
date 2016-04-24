package com.amozh.supplier;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;

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

    @Size(max = 50)
    @Column(unique = true, length = 50)
    private String name;

    @Size(max = 255)
    private String description;

    public Supplier(String name) {
        this.name = name;
    }

}
