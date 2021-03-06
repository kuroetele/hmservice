package com.oze.hmservice.controller;

import com.oze.hmservice.entity.Patient;
import com.oze.hmservice.pojos.DeletePatientRequest;
import com.oze.hmservice.repository.PatientRepository;
import com.oze.hmservice.service.PatientService;
import com.oze.hmservice.service.StaffService;
import com.oze.hmservice.utills.Response;
import lombok.AllArgsConstructor;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/patient")
@AllArgsConstructor
@Slf4j
public class PatientController {

    private final PatientService patientService;
    private final StaffService staffService;
    private final Response response;

    @GetMapping("/viewPatients")
    public ResponseEntity<List<Patient>> getPatientsWithAgeAboveTwo(@RequestHeader("staff-id") String staffUuid) {
         
        log.info("Get Patients List Staff ID Passed :"+staffUuid);
        
        if (staffService.staffExist(staffUuid.trim())) {
            return response.success(patientService.getPatients());
        }
        else {
            return response.failure("Authorization failed");
        }

    }

    @DeleteMapping("/deletePatients")
    public ResponseEntity<List<Patient>> deletePatients(@RequestHeader("staff-id") String staffUuid, @RequestBody DeletePatientRequest request) {

        log.info("deletePatients Staff ID Passed :"+staffUuid);

        if (staffService.staffExist(staffUuid.trim())) {
            return response.success(patientService.deletePatients(request));
        }
        else {
            return response.failure("Authorization failed");
        }

    }

    /*@GetMapping("/downloadPatient")
    public ResponseEntity<List<Patient>> downloadPatients(@RequestHeader("staff-id") String staffUuid, @RequestParam("id") Long patientId)
    {
        if(!staffService.staffExist(staffUuid))
            response.failure("Authorization failed");

        //return response.success(patientService.downloadPatient(patientId));
        return null;
    }*/
    @GetMapping("/downloadPatient")
    public ResponseEntity<Resource> downloadPatients(@RequestHeader("staff-id") String staffUuid, @RequestParam("id") Long patientId) {

        log.info("downloadPatients Staff ID Passed :"+staffUuid);

        if (staffService.staffExist(staffUuid)) {

            String filename = "patients.csv";
            InputStreamResource file = new InputStreamResource(patientService.load(patientId));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                    .contentType(MediaType.parseMediaType("application/csv"))
                    .body(file);
        }
        else {
            return response.failure("Authorization failed");
        }

    }

}
