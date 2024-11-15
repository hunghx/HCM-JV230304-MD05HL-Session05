package ra.apisecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ra.apisecurity.model.dto.request.FormLogin;
import ra.apisecurity.model.dto.request.FormRegister;
import ra.apisecurity.model.dto.response.JWTResponse;
import ra.apisecurity.service.IAuthenService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/public/auth")
public class AuthController {
    @Autowired
    private IAuthenService authenService;
    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login(@RequestBody FormLogin request){
        JWTResponse response = authenService.login(request);
        Map<String, Object> map = new HashMap<>();
        map.put("code",200);
        map.put("message","Login Successfully");
        map.put("data",response);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody FormRegister request){
        authenService.register(request);
        Map<String, Object> map = new HashMap<>();
        map.put("code",201);
        map.put("message","Register Successfully");
        return new ResponseEntity<>(map,HttpStatus.CREATED);
    }
}
