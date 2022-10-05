package com.example.qlnvproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "uri")
public class Uri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long uriId;

    private String uri;

    @OneToMany(mappedBy = "uri", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<RoleUri> uriList;
}
