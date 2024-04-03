package org.fktm.fastpickup.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthCheckRestController {
    
    @Value("${server.env}")
    private String env;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.serverAddress}")
    private String serverAddress;
    @Value("${serverName}")
    private String serverName;

    @GetMapping("/hc")
    public ResponseEntity<?> healthCheck(){

        Map<String,String> responseData = new HashMap<>();
        responseData.put("serverEnvTest", env);
        responseData.put("serverPort", serverPort);
        responseData.put("serverAddress", serverAddress);
        responseData.put("serverName", serverName);

        return ResponseEntity.ok(responseData);

    }

    @GetMapping("/env")
    public ResponseEntity<?> getEnv(){

        return ResponseEntity.ok(env);

    }

}
