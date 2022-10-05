package com.example.qlnvproject.service.impl;

import com.example.qlnvproject.model.Uri;
import com.example.qlnvproject.repository.UriReponsitory;
import com.example.qlnvproject.service.UriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UriServiceImpl implements UriService {
    @Autowired
    UriReponsitory uriReponsitory;
    @Override
    public Uri save(Uri uri) {
        return uriReponsitory.save(uri);
    }

    @Override
    public void deleteById(long id) {
        uriReponsitory.deleteById(id);
    }

    @Override
    public Uri findById(long id) {
        return uriReponsitory.findById(id).get();
    }

    @Override
    public List<Uri> getAll() {
        return uriReponsitory.findAll();
    }
}
