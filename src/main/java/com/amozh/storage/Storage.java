package com.amozh.storage;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andrii Mozharovskyi on 05.04.2016.
 */
@Entity
@Table(name="mb_Storage")
@Data
public class Storage {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private StorageType type;

    private String address;

    @OneToMany(mappedBy = "storage")
    private List<StorageItem> items;
}
