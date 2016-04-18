package com.amozh.item.model;

import com.amozh.operation.model.StockOperationType;
import com.amozh.operation.model.impl.HoldOperation;
import com.amozh.operation.model.impl.InOperation;
import com.amozh.storage.StorageItem;
import com.amozh.category.Category;
import com.fasterxml.jackson.annotation.*;
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
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public abstract class Item {
    @Id
    @GeneratedValue
    private long id;


    protected String name;

    protected String description;

    protected BigDecimal price = new BigDecimal(0);

    @Column(columnDefinition = "bit default 0", nullable = false)
    protected boolean deleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @OneToMany(mappedBy = "item")
    @JsonManagedReference
    private List<StorageItem> storageItems;

    public interface TestJsonView {}
}
