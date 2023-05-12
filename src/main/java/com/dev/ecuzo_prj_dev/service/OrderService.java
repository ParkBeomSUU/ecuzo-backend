package com.dev.ecuzo_prj_dev.service;

import com.dev.ecuzo_prj_dev.dto.OrderDto;

import java.util.List;

public interface OrderService {

    public void create(String userId, OrderDto orderDto,String token);

    public OrderDto orderSelect(String tableNum);

    public void orderDelete(String tableNum);
    public List<OrderDto> orderList();
    public void delete(int id);
    public void orderUpdate(String tableNum, OrderDto orderDto);
}
