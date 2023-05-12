package com.dev.ecuzo_prj_dev.jpa;

import com.dev.ecuzo_prj_dev.entity.SnsUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface SnsRepository extends JpaRepository<SnsUsers, Integer> {
    Optional<SnsUsers> findByEmail (String email);

}
