package com.ukma.protsyk.na.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created with IntelliJ IDEA.
 * User: okpr0814
 * Date: 5/4/17
 * Time: 9:19 AM
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class NodeController {

    @RequestMapping(value="/nodes", method= RequestMethod.GET)
    public String node(Model model) {

        return "nodes";
    }
}
