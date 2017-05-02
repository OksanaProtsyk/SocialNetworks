package com.ukma.protsyk.na.controller;

/**
 * Created by okpr0814 on 5/2/2017.
 */

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/")
    public String home() {
        return "index";
    }


}