package com.dev.ecuzo_prj_dev.service;

import com.dev.ecuzo_prj_dev.dto.OrderDto;
import com.dev.ecuzo_prj_dev.entity.Orders;
import com.dev.ecuzo_prj_dev.entity.TotalSales;
import com.dev.ecuzo_prj_dev.entity.Users;
import com.dev.ecuzo_prj_dev.jpa.OrdersRepository;
import com.dev.ecuzo_prj_dev.jpa.TotalSalesRepository;
import com.dev.ecuzo_prj_dev.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private OrdersRepository ordersRepository;
    private UserRepository userRepository;
    private TotalSalesRepository totalSalesRepository;


    @Autowired
    public OrderServiceImpl(OrdersRepository ordersRepository, UserRepository userRepository,TotalSalesRepository totalSalesRepository) {
        this.ordersRepository = ordersRepository;
        this.userRepository = userRepository;
        this.totalSalesRepository=totalSalesRepository;
    }

    @Transactional
    @Override
    public void create(String userId, OrderDto orderDto, String token) {
        System.out.println("orderDto = " + orderDto);
        orderDto.setToken(token);
        Users users = userRepository.findByUserId(userId);
        System.out.println("users = " + users);
        orderDto.setTableNum(users.getTableNum());
        orderDto.addUser(users);
        orderDto.setToken(token);
        Orders orders = orderDto.toEntity();
        TotalSales totalSales =orderDto.toTotalEntity();
        ordersRepository.save(orders);
        totalSalesRepository.save(totalSales);
    }

    @Transactional
    @Override
    public OrderDto orderSelect(String tableNum){
        Users users=userRepository.findByTableNum(tableNum);
        int id = users.getId();
        Orders orders = ordersRepository.findByUsersId(id);
        OrderDto orderdto =orders.toDto();
        System.out.println("orderdto = " + orderdto);
        return orderdto;
    }
    @Transactional
    @Override
    public List<OrderDto> orderList() {
        List<Orders> orders = ordersRepository.findAll();
        List<OrderDto> orderDto = orders.stream()
                .map(m -> m.toDto())
                .collect(Collectors.toList());
        return orderDto;
    }
    
    @Transactional
    @Override
    public void orderUpdate(String tableNum, OrderDto dto){
        Users users=userRepository.findByTableNum(tableNum);
        int id =users.getId();
        Orders orders =ordersRepository.findByUsersId(id);
        OrderDto update= orders.toDto();
        System.out.println("orders.getContent() = " + orders.getContent());
        update.setContent(orders.getContent()+dto.getContent());
        update.setTotalPrice(dto.getTotalPrice());
        orders=update.toEntity();
        ordersRepository.save(orders);
        System.out.println("DB 저장 완료");
    }

    @Transactional
    @Override
    public void orderDelete(String tableNum) {
        Users users=userRepository.findByTableNum(tableNum);
        log.info("users: "+users.getId());
        int id =users.getId();
        Orders orders= ordersRepository.findByUsersId(id);
        log.info("orders users: "+orders.getUsers().getUserId());
        ordersRepository.delete(orders);
    }

    @Override
    public void delete(int id) {

        Optional<Orders> orders =ordersRepository.findById(id);
        if(orders.isPresent()){
            ordersRepository.delete(orders.get());
        }

    }
}