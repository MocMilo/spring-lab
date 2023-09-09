package com.sandystack.exp.rest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Example endpoint secured with oAuth
 */
@RequestMapping("/secured")
@RestController
public class SecuredController {

    @GetMapping("/data")
    public String get() {
        return "Data secured with oAuth";
    }
}
