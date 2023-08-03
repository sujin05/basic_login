package com.victolee.signuplogin.controller;

import com.victolee.signuplogin.domain.entity.DataEntity;
import com.victolee.signuplogin.dto.DataDto;
import com.victolee.signuplogin.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( "/data")
public class DataController {
    private final DataService dataService;
    @Autowired
    public DataController(DataService dataService){
        this.dataService = dataService;
    }
   /* @GetMapping("/endpoint")
    public ResponseEntity<String> handleGetRequest() {
        // GET 요청 처리 로직
        // ...

        String response = "GET 요청이 성공적으로 처리되었습니다.9888";
        return ResponseEntity.ok(response);
    }
*/
    @PostMapping("/endpost")
    public ResponseEntity<String> createUser(@RequestBody DataEntity dataEntity) {
        dataService.saveData(dataEntity);
        String response = "POST 요청이 성공적으로 처리되었습니다.";
        System.out.println(dataEntity);
        return ResponseEntity.ok(response);
        //return "redirect:/user/login";
    }
    // 다른 HTTP 메서드 처리 메서드들 추가 가능

}

