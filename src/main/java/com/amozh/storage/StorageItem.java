package com.amozh.storage;

import com.amozh.item.model.Item;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by Andrii Mozharovskyi on 05.04.2016.
 */
@Entity
@Table(name="mb_StorageItem")
@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class StorageItem {
    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @NotNull
    @Min(0)
    private BigDecimal amount = BigDecimal.ZERO;

    @NotNull
    @Min(0)
    private BigDecimal hold = BigDecimal.ZERO;
}
