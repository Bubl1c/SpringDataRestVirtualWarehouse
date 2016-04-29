package com.amozh.operation.model;

import com.amozh.Const;
import com.amozh.operation.item.StockOperationItem;
import com.amozh.operation.model.impl.HoldOperation;
import com.amozh.operation.model.impl.InOperation;
import com.amozh.storage.Storage;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Andrii Mozharovskyi on 05.04.2016.
 */
@Entity
@Table(name="mb_stock_operation")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type", discriminatorType = DiscriminatorType.STRING)
@Data
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public abstract class StockOperation {
    @Id
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Size(max = 1000)
    @Column(length = 1000)
    @Lob
    private String description;

    @Transient
    @JsonProperty
    private StockOperationType type;

    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<StockOperationItem> items = new ArrayList<>();

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "storage_id")
    private Storage storage;

    @NotNull
    @JsonFormat(pattern = Const.DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = Const.DATE_TIME_PATTERN)
    private Date dateTimeCreated = new Date();

    @NotNull
    @JsonFormat(pattern = Const.DATE_TIME_PATTERN)
    @DateTimeFormat(pattern = Const.DATE_TIME_PATTERN)
    @Column(nullable = false)
    private Date dateTimePerformed;

    public void addItem(StockOperationItem item) {
        items.add(item);
    }

    public void setItems(Collection<StockOperationItem> items) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items.clear();
            this.items.addAll(items);
        }
    }

    public abstract StockOperationType getType();

    private void setType(StockOperationType type) {
        //Forbid to modify operation type
    }

}
