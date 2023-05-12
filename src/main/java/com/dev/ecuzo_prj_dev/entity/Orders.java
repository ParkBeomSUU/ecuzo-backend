package com.dev.ecuzo_prj_dev.entity;

import com.dev.ecuzo_prj_dev.dto.OrderDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.criterion.Order;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
public class Orders {
    @GeneratedValue
    @Id
    private int id;
    @Lob
    private String content;
    private Integer totalPrice;
    private String tableNumber;
    @Lob
    private String token;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @Builder
    public Orders(int id, String content, int totalPrice, String tableNumber, String token, LocalDateTime createAt, LocalDateTime updateAt, Users users) {
        this.id = id;
        this.token= token;
        this.content = content;
        this.tableNumber=tableNumber;
        this.totalPrice = totalPrice;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.users = users;
    }


    public OrderDto toDto(){
        OrderDto orderDto = OrderDto.builder()
                .id(this.id)
                .content(this.content)
                .totalPrice(this.totalPrice)
                .token(this.token)
                .tableNum(this.users.getTableNum())
                .userId(this.users.getUserId())
                .createAt(this.createAt)
                .updateAt(this.updateAt)
                .users(this.users)
                .build();
        return orderDto;
    }
}


