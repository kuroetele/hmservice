package com.oze.hmservice.repository;

import com.oze.hmservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT u FROM Patient u WHERE u.age > 2")
    List<Patient> findByPatientProfileAge();

    @Query("DELETE FROM Patient WHERE lastVisitDate BETWEEN :from and :to")
    void deletePatients(Date from, Date to);


}
