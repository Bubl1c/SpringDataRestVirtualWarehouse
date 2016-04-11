package com.amozh.item;

import com.amozh.storage.StorageItem;
import com.amozh.category.Category;
import lombok.Data;
import org.hibernate.annotations.DiscriminatorOptions;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Andrii Mozharovskyi on 06.04.2016.
 */
@Entity
@Table(name="mb_Product")
@Data
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type")
@DiscriminatorOptions(force = true)
public abstract class Item {
    @Id
    @GeneratedValue
    private long id;

    protected String name;

    protected String description;

    protected BigDecimal price = new BigDecimal(0);

    @Column(columnDefinition = "bit default 0", nullable = false)
    protected boolean deleted;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "item")
    private List<StorageItem> storageItems;
}
