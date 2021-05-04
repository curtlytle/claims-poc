package com.sif.claims.controllers;

import com.sif.claims.exceptions.CustomClaimException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping(path = "/customclaims")
public class ClaimsController {
    private static final int MAX = 9;
    private static final int MIN = 0;
    private final Random random = new Random();

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        log.info("----> In hello Controller for Claims Servic  <----");

        if (randomTest()) {
            throw new CustomClaimException("Failed Random test", new Exception());
        }

        return new ResponseEntity<>("Successful call to custom endpoint... Hello Claims World!", HttpStatus.OK);
    }

    @GetMapping("/hello2/{hvalue}")
    public ResponseEntity<String> helloParam(@PathVariable String hvalue) {
        log.info("----> In hello Controller for Claims Servic ... param {} <----", hvalue);

        return new ResponseEntity<>(String.format(
                "Successful call to custom endpoint...with Path variable %s", hvalue), HttpStatus.OK);
    }

    private boolean randomTest() {
        int randomInt = random.nextInt(MAX - MIN + 1) + MIN;
        log.info("---> RANDOM INT = " + randomInt);

        return randomInt > 5;
    }
}
