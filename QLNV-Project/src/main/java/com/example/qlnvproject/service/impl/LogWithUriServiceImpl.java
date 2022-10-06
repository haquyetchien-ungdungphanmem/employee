package com.example.qlnvproject.service.impl;

import com.example.qlnvproject.dto.LogWithUriDto;
import com.example.qlnvproject.model.LogWithUri;
import com.example.qlnvproject.model.Uri;
import com.example.qlnvproject.repository.LogWithUriReponsitory;
import com.example.qlnvproject.repository.UriReponsitory;
import com.example.qlnvproject.service.LogWithUriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogWithUriServiceImpl implements LogWithUriService {

    @Autowired
    LogWithUriReponsitory logWithUriReponsitory;

    @Autowired
    UriReponsitory uriReponsitory;

    @Override
    public LogWithUri save(LogWithUriDto logWithUriDto,LogWithUri logWithUri) {
        Uri uri = uriReponsitory.findById(logWithUriDto.getUri()).get();
       if (logWithUriReponsitory.findByUri(uri) == null){
           logWithUri.setUri(uri);
           return logWithUriReponsitory.save(logWithUri);
       }else {
           return null;
       }
    }

    @Override
    public LogWithUri findByUri(String uri) {
        Uri uriFind = uriReponsitory.findByUri(uri);
        return logWithUriReponsitory.findByUri(uriFind);
    }

    @Override
    public void delete(long id) {
        logWithUriReponsitory.deleteById(id);
    }
}
