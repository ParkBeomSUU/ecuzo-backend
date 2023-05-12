package com.dev.ecuzo_prj_dev.dto;

import com.dev.ecuzo_prj_dev.entity.SnsUsers;
import com.dev.ecuzo_prj_dev.entity.Role;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@Data
public class SnsUserDto {

    private int id;
    private String email;
    private String nickname;
    private String imagePath;
    private String imageName;
    private Role roleType;

    public SnsUsers toEntity() {
        SnsUsers snsUsers = SnsUsers.builder()
                .id(this.id)
                .email(this.email)
                .nickname(this.nickname)
                .imagePath(this.imagePath)
                .imageName(this.imageName)
                .roleType(this.roleType)
                .build();
        return snsUsers;
    }

    @Builder
    public SnsUserDto(int id, String email, String nickname, String imagePath, String imageName, Role roleType) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.imagePath = imagePath;
        this.imageName = imageName;
        this.roleType = roleType;
    }
}
