package com.amozh.operation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Andrii Mozharovskyi on 05.04.2016.
 */
@Entity
@Table(name="mb_stock_operation")
@Data
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
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
    @JsonView(StockOperationItem.OperationsList.class)
    private StockOperationType type;

    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<StockOperationItem> items = new ArrayList<>();

    private Date dateTimeCreated = new Date();

    private Date dateTimePerformed;

    public void addItem(StockOperationItem item) {
        items.add(item);
    }

    /**
     * Type field value set in subclasses and should not be modified from outside
     * @param type
     */
    protected void setType(StockOperationType type) {
        this.type = type;
    }

}
