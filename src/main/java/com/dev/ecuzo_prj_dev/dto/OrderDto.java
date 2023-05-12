package com.dev.ecuzo_prj_dev.dto;

import com.dev.ecuzo_prj_dev.entity.Orders;
import com.dev.ecuzo_prj_dev.entity.SnsUsers;
import com.dev.ecuzo_prj_dev.entity.TotalSales;
import com.dev.ecuzo_prj_dev.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@Data
@Builder
public class OrderDto {

    private int id;
    private String content;
    private Integer totalPrice;
    private  String token;
    private String userId;
    private String tableNum;
    private String userNick;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    @JsonIgnore
    private Users users;
    @JsonIgnore
    private SnsUsers snsUsers;

    public Orders toEntity(){
        Orders orders = Orders.builder()
                .id(this.id)
                .token(this.token)
                .content(this.content)
                .totalPrice(this.totalPrice)
                .tableNumber(this.tableNum)
                .createAt(this.createAt)
                .updateAt(this.updateAt)
                .users(this.users)
                .build();
        return orders;
    }

    public TotalSales toTotalEntity(){
        TotalSales totalSales = TotalSales.builder()
                .id(this.id)
                .token(this.token)
                .content(this.content)
                .totalPrice(this.totalPrice)
                .createAt(this.createAt)
                .updateAt(this.updateAt)
                .users(this.users)
                .build();
        return totalSales;
    }
    public void addUser(Users users){
        this.users=users;
    }


}
