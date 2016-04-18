package com.amozh.operation.model;

import com.amozh.operation.model.impl.HoldOperation;
import com.amozh.operation.model.impl.InOperation;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii Mozharovskyi on 05.04.2016.
 */
@Entity
//@Table(name="mb_stock_operation")
@Data
@NoArgsConstructor
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = InOperation.class, name = StockOperationType.IN_OPERATION_NAME),
        @JsonSubTypes.Type(value = HoldOperation.class, name = StockOperationType.HOLD_OPERATION_NAME) })
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public abstract class StockOperation {
    @Id
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @GeneratedValue(generator = "uuid2")
//    @Column(columnDefinition="uniqueidentifier")
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.TABLE)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
//    private UUID uuid;
    private String id;

    private String description;

    @Transient
    private StockOperationType type;

    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL)
    private Collection<StockOperationItem> items = new ArrayList<>();

    private Date dateTimeCreated = new Date();

    @Column(nullable = false)
    private Date dateTimePerformed;

    public void addItem(StockOperationItem item) {
        items.add(item);
    }

    public void setItems(Collection<StockOperationItem> items) {
        this.items = items != null ? items : new ArrayList<>();
    }

    public abstract StockOperationType getType();

}
