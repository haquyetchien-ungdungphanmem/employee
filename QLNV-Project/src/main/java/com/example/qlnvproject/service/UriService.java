package com.example.qlnvproject.service;

import com.example.qlnvproject.model.Uri;

import java.util.List;

public interface UriService {
    Uri save(Uri uri);

    void deleteById(long id);

    Uri findById(long id);

    List<Uri> getAll();

    Uri findByUri(String requestUri);
}
