package com.dev.ecuzo_prj_dev.jpa;

import com.dev.ecuzo_prj_dev.dto.UserDto;
import com.dev.ecuzo_prj_dev.entity.Orders;
import com.dev.ecuzo_prj_dev.entity.Users;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Users findByUserId(String userId);
    Users findByTableNum(String tableNum);
    List<Users> findAllByTableNum(String tableNum);
    boolean existsByUserId(String userId);

}
