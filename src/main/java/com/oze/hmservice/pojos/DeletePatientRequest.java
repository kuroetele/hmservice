package com.oze.hmservice.pojos;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Data
@Getter
@Setter
@NoArgsConstructor
public class DeletePatientRequest {
    protected Date from;
    protected  Date to;
}