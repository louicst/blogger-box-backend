package com.dauphine.blogger_box_backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    // Un deuxième pour tester les paramètres (souvent vu en Session 03)
    @GetMapping("hello")
    public String helloByName(@RequestParam String name) {
        return "Hello " + name;
    }

    @GetMapping("hello/{name}")
    public String hello(@PathVariable String name) {
        return "Hello " + name;
    }
}