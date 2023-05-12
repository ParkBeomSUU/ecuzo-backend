package com.dev.ecuzo_prj_dev.entity;


import com.dev.ecuzo_prj_dev.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@Getter
@Entity
public class Users {

    @GeneratedValue
    @Id
    private int id;
    private String userId;
    private String userPw;
    private String tableNum;
    @Enumerated(EnumType.STRING)
    private Role roleType;
    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;
    @JsonIgnore
    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Orders> orders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<TotalSales> totalSales = new ArrayList<>();

    public UserDto toDto(){
        UserDto userDto = UserDto.builder()
                .id(this.id)
                .userId(this.userId)
                .roleType(this.roleType)
                .tableNum(this.tableNum)
                .createAt(this.createAt)
                .orders(this.orders)
                .updateAt(this.updateAt)
                .build();
        return userDto;
    }

    @Builder
    public Users(int id, String userId, String userPw,String tableNum, Role roleType, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.userId = userId;
        this.userPw = userPw;
        this.tableNum=tableNum;
        this.roleType = roleType;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
