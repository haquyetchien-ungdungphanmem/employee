package com.example.qlnvproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "roleUri")
public class RoleUri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    @JsonManagedReference
    private Role role;

    @ManyToOne
    @JoinColumn(name = "uriId", referencedColumnName = "uriId")
    @JsonManagedReference
    private Uri uri;
}
