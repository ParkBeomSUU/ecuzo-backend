package com.dev.ecuzo_prj_dev.entity;


import com.dev.ecuzo_prj_dev.dto.SnsUserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class SnsUsers {
    @Id
    @GeneratedValue
    int id;
    private String email;
    private String nickname;
    private String imagePath;
    private String imageName;
    @Enumerated(EnumType.STRING)
    private Role roleType;
    @CreationTimestamp
    private LocalDateTime createAt ;

    @Builder
    public SnsUsers(int id, String email, String nickname, String imagePath, String imageName, Role roleType) {
        this.id =id;
        this.email = email;
        this.nickname = nickname;
        this.imagePath = imagePath;
        this.imageName = imageName;
        this.roleType =roleType;
    }

    public SnsUserDto toDto(){
        SnsUserDto snsUserDto = SnsUserDto.builder()
                .id(this.id)
                .email(this.email)
                .nickname(this.nickname)
                .imagePath(this.imagePath)
                .imageName(this.imageName)
                .roleType(this.roleType)
                .build();
        return snsUserDto;

    }


}
