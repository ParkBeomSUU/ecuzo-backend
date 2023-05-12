package com.dev.ecuzo_prj_dev.entity;

import com.dev.ecuzo_prj_dev.dto.TotalSalesDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Builder
public class TotalSales {
        @GeneratedValue
        @Id
        private int id;
        @Lob
        private String content;
        private Integer totalPrice;
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
        public TotalSales(int id, String content, int totalPrice, String token, LocalDateTime createAt, LocalDateTime updateAt, Users users) {
            this.id = id;
            this.token= token;
            this.content = content;
            this.totalPrice = totalPrice;
            this.createAt = createAt;
            this.updateAt = updateAt;
            this.users = users;
        }
        public TotalSalesDto toDto(){
            TotalSalesDto totalSalesDto = TotalSalesDto.builder()
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
            return totalSalesDto;
        }
    }

