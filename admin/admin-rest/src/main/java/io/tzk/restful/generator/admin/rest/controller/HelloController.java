package io.tzk.restful.generator.admin.rest.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    @PreAuthorize("hasAuthority('GET:/')")
    public String hello(){
        return "hello";
    }
}
