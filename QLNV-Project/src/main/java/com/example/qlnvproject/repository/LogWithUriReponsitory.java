package com.example.qlnvproject.repository;

import com.example.qlnvproject.model.LogWithUri;
import com.example.qlnvproject.model.Uri;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogWithUriReponsitory extends JpaRepository<LogWithUri, Long> {
    LogWithUri findByUri(Uri uri);

}
