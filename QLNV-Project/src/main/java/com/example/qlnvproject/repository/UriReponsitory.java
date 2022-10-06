package com.example.qlnvproject.repository;

import com.example.qlnvproject.model.Uri;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UriReponsitory extends JpaRepository<Uri, Long> {
    Uri findByUri(String uri);
}
