package com.amozh.storage;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonValueInstantiator;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotEmpty
    @Size(max = 50)
    @Column(length = 50)
    private String name;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StorageType type;

    private String address;

}
