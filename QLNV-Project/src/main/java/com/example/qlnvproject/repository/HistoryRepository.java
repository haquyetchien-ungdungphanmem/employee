package com.example.qlnvproject.repository;

import com.example.qlnvproject.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {

    List<History> findByStatuss(int status);
}
