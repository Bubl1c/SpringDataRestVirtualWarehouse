package com.amozh;

import com.amozh.operation.model.StockOperationItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Andrii Mozharovskyi on 18.04.2016.
 */
@Entity
@Data
@NoArgsConstructor
public class TestInner {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    private TestOuter parent;
}
