package com.lab.server.rest;

import com.lab.server.dto.TestRequest;
import com.lab.server.rabbitmq.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TestController {
    private final Producer producer;
    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody TestRequest req){
        producer.send(req.getMessage());
        return ResponseEntity.ok("message success");
    }

    @PostMapping("/direct/send")
    public ResponseEntity<String> sendDirect(@RequestBody TestRequest req){
        producer.directSend(req.getMessage());
        return ResponseEntity.ok("message success");
    }
}
