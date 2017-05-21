package com.ukma.protsyk.na.controller;

import com.ukma.protsyk.na.gephi.ExportImport;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by okpr0814 on 5/20/2017.
 */

@Controller
public class VisualizeController {

    @RequestMapping(value="/vis", method= RequestMethod.GET)
    public String home(Model model){
        ExportImport ei = new ExportImport();
        ei.script("20295348");
        return "nodes";
    }
}
