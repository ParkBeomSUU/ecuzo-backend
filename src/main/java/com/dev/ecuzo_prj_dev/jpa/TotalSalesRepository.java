package com.dev.ecuzo_prj_dev.jpa;

import com.dev.ecuzo_prj_dev.entity.TotalSales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TotalSalesRepository extends JpaRepository<TotalSales,Integer> {
    List<TotalSales> findAllById(int id);
}
