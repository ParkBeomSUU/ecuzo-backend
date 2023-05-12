package com.dev.ecuzo_prj_dev.jpa;

import com.dev.ecuzo_prj_dev.entity.Orders;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {
    List<Orders> findAllByUsersId(int id);
    Orders findByUsersId(int id);

}