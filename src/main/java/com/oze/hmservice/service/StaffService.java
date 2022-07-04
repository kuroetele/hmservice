package com.oze.hmservice.service;
import com.oze.hmservice.entity.Staff;
import com.oze.hmservice.repository.StaffRepository;
import java.util.UUID;

import com.oze.hmservice.utills.App;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor

public class StaffService {

    private final StaffRepository staffRepository;
    private  final App app;

    /*
     * Method to save staff member details
     * */
    @Transactional
    public Staff saveStaff(Staff sMember) {
        sMember.setUuid(UUID.randomUUID().toString());
        return staffRepository.save(sMember);
    }

    /*
    * Method to update the staff details
    * */
    public Staff updateStaff(String uuid, Staff staff) {
        Staff existingStaff = staffRepository.findByUuid(uuid).orElse(null);
        if(existingStaff!=null){
            existingStaff.setName(staff.getName());
            return staffRepository.save(existingStaff);
        }else {
            return null;
        }
    }

    public Staff getStaff(String uuid){
        return  staffRepository.findByUuid(uuid).orElse(null);
    }

    public Boolean staffExist(String uuid){
        Staff staff=  staffRepository.findByUuid(uuid).orElse(null);
        return  staff!=null?true:false;
    }

}
