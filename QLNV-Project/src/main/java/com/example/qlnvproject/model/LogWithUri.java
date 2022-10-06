package com.example.qlnvproject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "logWithUri")
public class LogWithUri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "uriId", referencedColumnName = "uriId")
    @JsonManagedReference
    private Uri uri;
}
