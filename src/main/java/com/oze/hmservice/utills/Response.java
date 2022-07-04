package com.oze.hmservice.utills;
import com.syrol.lenos.pojos.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class Response<T> {

    public ResponseEntity<APIResponse> success(T payload) {
        return ResponseEntity.ok().body(new APIResponse<>("Request Successful", true, payload));
    }

    public ResponseEntity<APIResponse> failure(String message) {
        return ResponseEntity.badRequest().body(new APIResponse<>(message, false, null));
    }
}
