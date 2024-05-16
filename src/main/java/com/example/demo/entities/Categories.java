package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name = "Categories")
public class Categories {
    @Id
    @Column(name = "CATEGORYID")
    private Integer categoryId;
    @Column(name = "CATEGORYNAME")
    private String categoryName;
    @Column(name = "CATEGORYDESC")
    private String categoryDesc;

    @OneToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SubCategories> subCategories;

}
