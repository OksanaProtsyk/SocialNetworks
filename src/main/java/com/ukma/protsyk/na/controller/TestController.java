package com.ukma.protsyk.na.controller;

import com.ukma.protsyk.na.gephi.AgoCircleDiscovery;
import com.ukma.protsyk.na.gephi.AgoTagCircle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by okpr0814 on 5/26/2017.
 */

@Controller
public class TestController {
    @RequestMapping(value="/test", method= RequestMethod.GET)

    public String detectCommunity(Model model) {
        AgoCircleDiscovery comDet = new AgoCircleDiscovery();
        comDet.script();
        return "nodes";
    }
}
