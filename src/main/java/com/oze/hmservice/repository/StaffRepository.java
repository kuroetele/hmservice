package com.oze.hmservice.repository;

import com.oze.hmservice.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {

    Optional<Staff> findByUuid(String uuid);
   
}
