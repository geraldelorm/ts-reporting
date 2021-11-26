package io.turntabl.tsrs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @GetMapping
    public String home(){
        return "Hello, Welcome to Reporting Service";
    }
}
