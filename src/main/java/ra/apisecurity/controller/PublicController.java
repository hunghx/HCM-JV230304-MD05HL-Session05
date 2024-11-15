package ra.apisecurity.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @GetMapping("/v1/list")
    public ResponseEntity<String> getUserData(){
        return new ResponseEntity<>("Thanh công", HttpStatus.OK);
    }
}
