package me.escoffier.pirate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AhoySpring {

    @GetMapping(path = "/spring/ahoy")
    public String ahoy() {
        return "Bring a Spring Upon ‘er - "  + Thread.currentThread().getName();
    }

}
