package com.ukma.protsyk.na.controller;

import com.ukma.protsyk.na.gephi.AgoCircleDiscovery;
import com.ukma.protsyk.na.gephi.AgoTagCircle;
import com.ukma.protsyk.na.gephi.CommunityDetection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by okpr0814 on 5/15/2017.
 */
@Controller
public class AgoCircleTagController {
    @RequestMapping(value="/circle", method= RequestMethod.GET)

    public String detectCommunity(Model model) {
        AgoTagCircle comDet = new AgoTagCircle();
        comDet.script("graph_my");
        return "nodes";
    }
    }
