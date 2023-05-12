package com.dev.ecuzo_prj_dev.controller;

import com.dev.ecuzo_prj_dev.dto.OrderDto;
import com.dev.ecuzo_prj_dev.dto.TotalSalesDto;
import com.dev.ecuzo_prj_dev.service.TotalSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/")
@RestController
public class TotalSalesController {

    final private TotalSalesService totalSalesService;
    @Autowired
    public TotalSalesController(TotalSalesService totalSalesService) {
        this.totalSalesService= totalSalesService;
    }
    @GetMapping("total")
    public ResponseEntity<List<TotalSalesDto>> totalList(){
        return ResponseEntity.ok(totalSalesService.totalSalesList());
    }

//    @GetMapping("order/{id}")
//    public ResponseEntity<OrderDto> orderSelect(@PathVariable int id){
//        return ResponseEntity.ok(orderService.orderSelect(id));
//    }

    @PutMapping("total/{tableNum}")
    public String totalUpdate(@PathVariable String tableNum, @RequestBody TotalSalesDto totalSalesDto){
        totalSalesService.totalSalesUpdate(tableNum,totalSalesDto);
        return "UPDATE ORDER COMPLETE";
    }

//    @DeleteMapping("total/{tableNum}")
//    public String totalDelete(@PathVariable String tableNum){
//        totalSalesService.totalSalesDelete(tableNum);
//        return "DELETE ORDER COMPLETE";
//    }
}
