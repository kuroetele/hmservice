package com.oze.hmservice.controller;

import com.oze.hmservice.entity.Staff;
import com.oze.hmservice.service.StaffService;
import com.oze.hmservice.utills.Response;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/staffs")
@AllArgsConstructor
public class StaffController {

    private final StaffService staffService;
    private final Response response;

    @PostMapping("/createStaff")
    public ResponseEntity<Staff> createStaffProfile(@RequestBody @Valid Staff staff)
    {
        if(staff == null || staff.getName().equals(""))
            return response.failure("Validation error, please input staff name");
         else
            return response.success(staffService.saveStaff(staff));
    }

    @PutMapping("/updateStaff")
    public ResponseEntity<Staff> updateStaffProfile(@RequestParam("id") String staffUuid, @RequestBody @Valid Staff staff)
    {
        if(!staffService.staffExist(staffUuid))
            response.failure("Authorization failed");

        Staff updatedStaff=staffService.updateStaff(staffUuid,staff);
        if(updatedStaff!=null)
        return  response.success(updatedStaff);

        return response.failure("Unable to update staff");
    }

}
