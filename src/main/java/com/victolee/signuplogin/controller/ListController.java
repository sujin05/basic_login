package com.victolee.signuplogin.controller;

import com.victolee.signuplogin.dto.DataDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/list")
public class ListController {
    @GetMapping("/{email}")
    public ResponseEntity<String> handleGetRequest() {
        // GET 요청 처리 로직
        // ...

        String response = "GET 요청이 성공적으로 처리되었습니다.9888";
        return ResponseEntity.ok(response);
    }

}
