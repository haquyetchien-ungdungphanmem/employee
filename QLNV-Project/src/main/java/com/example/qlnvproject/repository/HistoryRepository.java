package com.example.qlnvproject.repository;

import com.example.qlnvproject.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Integer> {
}
