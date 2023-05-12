package com.dev.ecuzo_prj_dev.controller;

import com.dev.ecuzo_prj_dev.dto.OrderDto;
import com.dev.ecuzo_prj_dev.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/")
@RestController
public class OrderController {

    final private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "create")
    public ResponseEntity<?> orderAdd(@RequestHeader HttpHeaders headers, @AuthenticationPrincipal UserDetails userDetails, @RequestBody OrderDto orderDto){
        log.info("Order Controller");
        String userId =userDetails.getUsername();
        String token = headers.toSingleValueMap().get("authorization").substring(7);
        log.info("token정보:"+token);
        log.info("현재 접속 유저아이디"+userId);
        System.out.println("orderDto = " + orderDto);
        orderService.create(userId ,orderDto, token);
        return ResponseEntity.ok("CREATE ORDER COMPLETE");
    }

    @GetMapping("order/list")
    public ResponseEntity<List<OrderDto>> orderList(){
        return ResponseEntity.ok(orderService.orderList());
    }

    @GetMapping("order/{tableNum}")
    public ResponseEntity<OrderDto> orderSelect(@PathVariable String tableNum){
        return ResponseEntity.ok(orderService.orderSelect(tableNum));
    }

    @PutMapping("update/{tableNum}")
    public String orderUpdate(@PathVariable String tableNum, @RequestBody OrderDto orderDto){
        System.out.println("orderDto = " + orderDto.getContent());
        orderService.orderUpdate(tableNum,orderDto);
        return "UPDATE ORDER COMPLETE";
    }

    @DeleteMapping("delete/{tableNum}")
    public String orderDelete(@PathVariable String tableNum){
        System.out.println("tableNum = " + tableNum);
        orderService.orderDelete(tableNum);
        return "DELETE ORDER COMPLETE";
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable int id){
        orderService.delete(id);
    }
}
