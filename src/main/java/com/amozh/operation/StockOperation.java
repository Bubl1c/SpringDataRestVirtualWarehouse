package com.amozh.operation;

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
public class StockOperation {
    @Id
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @GeneratedValue(generator = "uuid2")
//    @Column(columnDefinition="uniqueidentifier")
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.TABLE)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
//    private UUID uuid;
    private String id;

    private String description;

    @Enumerated(EnumType.STRING)
    private StockOperationType type;

    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL)
    private List<StockOperationItem> items = new ArrayList<>();

    private Date dateTimeCreated = new Date();

    private Date dateTimePerformed;

    public void addItem(StockOperationItem item) {
        items.add(item);
    }

}
