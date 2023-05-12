package com.dev.ecuzo_prj_dev.dto;

import com.dev.ecuzo_prj_dev.entity.Orders;
import com.dev.ecuzo_prj_dev.entity.Role;
import com.dev.ecuzo_prj_dev.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Data
public class UserDto {
    private int id;
    private String userId;
    private String userPw;
    private String tableNum;
    private Role roleType;
    private List<Orders> orders;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

//    public static UserDto entityToDto(Users users){
//        return new UserDto(users.getId(), users.getUserId(), users.getUserPw(), users.getUserNick(),
//        users.getEmail(), users.getRoleType(),users.getOrders(),users.getCreateAt(),users.getUpdateAt());
//    }
//    public static Users dtoToEntity(UserDto userDto){
//        return  new Users(userDto.getId(),userDto.getUserId(),userDto.getUserPw(), userDto.getUserNick(),
//                userDto.getEmail(),userDto.getRoleType(),userDto.updateAt,userDto.createAt, userDto.getOrders());
//    }
    public Users toEntity(){
        Users users = Users.builder()
                .id(this.id)
                .userId(this.userId)
                .userPw(this.userPw)
                .tableNum(this.tableNum)
                .roleType(this.roleType)
                .createAt(this.createAt)
                .updateAt(this.updateAt)
                .build();
        return users;
    }

    @Builder
    public UserDto(int id, String userId, String tableNum, Role roleType, List<Orders> orders, LocalDateTime createAt, LocalDateTime updateAt) {
        this.id = id;
        this.userId = userId;
        this.tableNum=tableNum;
        this.orders=orders;
        this.createAt = createAt;
        this.updateAt = updateAt;
    }
}
