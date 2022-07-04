package com.oze.hmservice.service;

import com.oze.hmservice.entity.Patient;
import com.oze.hmservice.pojos.DeletePatientRequest;
import com.oze.hmservice.repository.PatientRepository;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
/*import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;*/
@Service
@Transactional
@AllArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;


    public List<Patient> getPatients() {
        return patientRepository.findByPatientProfileAge();
    }

    public boolean deletePatients(DeletePatientRequest request){
         patientRepository.deletePatients(request.getFrom(), request.getTo());
         return  true;
    }

    /*public static ByteArrayInputStream patientsToCSV(List<Patient> patients) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Patient patient : patients) {
                List<String> data = Arrays.asList(
                        String.valueOf(patient.getId()),
                        patient.getName(),
                        patient.getAge(),
                        String.valueOf(patient.getLastVisitDate())
                );
                csvPrinter.printRecord(data);
            }
            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }*/

    /*public ByteArrayInputStream load(long patientId) {
        List<Patient> patients = null;
        Patient patient=patientRepository.findById(patientId).orElse(null);
        if(patient!=null){
            patients =  Arrays.asList(patient);
        }else {
        }
        ByteArrayInputStream in = patientsToCSV(patients);
        return in;
    }*/

    /*public String downloadPatient(Long patientId) {
        String header="Name,Age,LastVisitedDate";
        String csvDelimeter="";
        Patient patient=patientRepository.findById(patientId).orElse(null);
        if(patient!=null){
            String row=patient.getName()+","+patient.getAge()+","+patient.getLastVisitDate();
            csvDelimeter=header+"\n"+row;
            return csvDelimeter;
        }
        return null;
    }*/


}
