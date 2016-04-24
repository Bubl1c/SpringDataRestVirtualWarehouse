package com.amozh.operation.item;

import com.amozh.item.model.Item;
import com.amozh.operation.model.StockOperation;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Andrii Mozharovskyi on 06.04.2016.
 */
@Entity
@Table(name="mb_stock_operation_item")
@Data
@NoArgsConstructor
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class StockOperationItem {
    @Id
//    @GenericGenerator(name = "uuid2", strategy = "uuid2")
//    @GeneratedValue(generator = "uuid2")
//    @Column(columnDefinition="uniqueidentifier")
    @GeneratedValue(generator = "system-uuid", strategy = GenerationType.TABLE)
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
//    private UUID uuid;
    private String id;

    @ManyToOne
//    @JoinColumn(name = "operation_id", columnDefinition="uniqueidentifier")
    @JoinColumn(name = "operation_id")
    private StockOperation operation;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private BigDecimal amount;

    private BigDecimal price;

    private BigDecimal amountAfter;

    public StockOperationItem(Item item, BigDecimal amount, BigDecimal price, BigDecimal amountAfter) {
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.amountAfter = amountAfter;
    }

}
